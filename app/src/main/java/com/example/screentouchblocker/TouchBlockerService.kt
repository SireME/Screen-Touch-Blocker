package com.example.screentouchblocker

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.provider.Settings
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.graphics.Color
import android.os.Handler
import android.view.GestureDetector


class TouchBlockerService : Service() {
    private var windowManager: WindowManager? = null
    private var leftBlocker: View? = null
    private var rightBlocker: View? = null
    private var blockerWidth: Int = DEFAULT_BLOCKER_WIDTH

    private lateinit var gestureDetector: GestureDetector

    override fun onCreate() {
        super.onCreate()
        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        createOverlay()
        startForegroundService()
    }

    private fun createOverlay() {
        // Initialize GestureDetector
        val gestureListener = object : GestureDetector.SimpleOnGestureListener() {
            override fun onLongPress(e: MotionEvent) {
                // Intercept and "destroy" the long press event
                super.onLongPress(e)
                // You could log here if needed
            }

            override fun onDown(e: MotionEvent): Boolean {
                // Always return true to consume the touch event
                return true
            }
        }

        gestureDetector = GestureDetector(this, gestureListener)

        leftBlocker = View(this).apply {
            setBackgroundColor(Color.parseColor("#800000FF"))
            setOnTouchListener { _, event ->
                gestureDetector.onTouchEvent(event)
                true  // Consume the touch event to block it
            }
        }

        rightBlocker = View(this).apply {
            setBackgroundColor(Color.parseColor("#800000FF"))
            setOnTouchListener { _, event ->
                gestureDetector.onTouchEvent(event)
                true  // Consume the touch event to block it
            }
        }

        val leftParams = WindowManager.LayoutParams(
            blockerWidth, WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        ).apply { gravity = Gravity.START }

        val rightParams = WindowManager.LayoutParams(
            blockerWidth, WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        ).apply { gravity = Gravity.END }

        windowManager?.addView(leftBlocker, leftParams)
        windowManager?.addView(rightBlocker, rightParams)
    }

    private fun updateOverlay() {
        leftBlocker?.let { windowManager?.removeView(it) }
        rightBlocker?.let { windowManager?.removeView(it) }
        createOverlay()
    }

    private fun startForegroundService() {
        val channelId = "touch_blocker_service"
        val notificationManager = getSystemService(NotificationManager::class.java)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId, "Touch Blocker", NotificationManager.IMPORTANCE_LOW
            )
            notificationManager?.createNotificationChannel(channel)
        }

        val notification = Notification.Builder(this, channelId)
            .setContentTitle("Touch Blocker Running")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()

        startForeground(1, notification)
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        if (!Settings.canDrawOverlays(this)) {
            stopSelf()
            return START_NOT_STICKY
        }

        val newWidth = intent.getIntExtra(EXTRA_BLOCKER_WIDTH, DEFAULT_BLOCKER_WIDTH)
        if (newWidth != blockerWidth) {
            blockerWidth = newWidth
            updateOverlay()
        }

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        leftBlocker?.let { windowManager?.removeView(it) }
        rightBlocker?.let { windowManager?.removeView(it) }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    companion object {
        private const val DEFAULT_BLOCKER_WIDTH = 30
        const val EXTRA_BLOCKER_WIDTH = "extra_blocker_width"
    }
}