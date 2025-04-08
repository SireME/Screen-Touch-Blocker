📱 Screen Touch Blocker
=======================

**Screen Touch Blocker** is a lightweight Android utility that creates invisible overlays on the sides of the screen to block unwanted touches — perfect for gaming, video playback, reading apps or faulty self-touching screens.

* * *

✨ Features
----------

*   🔒 Blocks screen edges to prevent accidental touches
    
*   🚀 Runs as a foreground service for reliability
    
*   🔁 Automatically restarts on boot
    
*   🎛️ Configurable blocker width
    
*   📱 Simple UI to start the service
    

* * *

🧪 Permissions Required
-----------------------

*   `SYSTEM_ALERT_WINDOW`: To draw overlays on top of other apps
    
*   `FOREGROUND_SERVICE`: To keep the blocker running in the background
    
*   `RECEIVE_BOOT_COMPLETED`: To restart service on device boot
    
*   `FOREGROUND_SERVICE_SYSTEM_EXEMPTED`, `SCHEDULE_EXACT_ALARM`: Optional for future use
    

* * *

📦 Installation
---------------

You can install **Screen Touch Blocker** in one of the following ways:

* * *

### 🔧 Method 1: Android Studio

1.  Clone or download this project:
    
    bash
    
    Copy Edit
    
    `git clone git@github.com:SireME/Screen-Touch-Blocker.git`
    
2.  Open the project in **Android Studio**.
    
3.  Plug in your device (with Developer Mode and USB Debugging enabled).
    
4.  Click **Run ▶** or use `Shift + F10` to build and deploy the app to your device.
    

* * *

### 📦 Method 2: ADB (via APK)

> This method is ideal if you just want to install and try the app without building it.

1.  Ensure [ADB](https://developer.android.com/studio/command-line/adb) is installed.
    
2.  Connect your device via USB (or ensure it's visible via `adb devices`).
    
3.  From the project root directory (where the APK is stored), run:
    
    bash
    
    Copy Edit
    
    `adb install screen-touch-blocker.apk`
    
4.  Launch the app from your device.
    

> **Note:** You may need to allow "Install from unknown sources" on your device for this to work.

* * *

🚀 How to Use
-------------

1.  Open the app and tap **“Start Touch Blocker”**.
    
2.  Grant overlay permission when prompted.
    
3.  The app will begin blocking touch input on the left and right edges of the screen.
    
4.  To stop the blocker, kill the service manually or add a stop button (not yet implemented).
    

* * *

🔧 Customization
----------------

*   **Change blocker width:** Modify `DEFAULT_BLOCKER_WIDTH` in `TouchBlockerService.kt`.
    
*   **Change overlay color:** Set transparent or custom colors using `setBackgroundColor(...)`.
    

* * *

🛠 Boot Behavior
----------------

The app includes a `BroadcastReceiver` that listens for device reboots (`BOOT_COMPLETED`) and can restart the blocker service automatically.

* * *

🧰 For Developers
-----------------

*   Compatible with Android API 23+
    
*   Uses `TYPE_APPLICATION_OVERLAY` for overlay windows
    
*   Foreground service compliance for Android 8.0+
    

* * *

📄 License
----------

MIT License 
