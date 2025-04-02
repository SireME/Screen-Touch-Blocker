package com.example.screentouchblocker

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            val serviceIntent = Intent(context, TouchBlockerService::class.java)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // For Android 8.0+ (API 26+)
                context.startForegroundService(serviceIntent)
            } else {
                // For Android 7.1 & below (API 25-)
                context.startService(serviceIntent)
            }
        }
    }
}
