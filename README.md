* * *

📱 Screen Touch Blocker
=======================

**Screen Touch Blocker** is a lightweight Android utility app that creates invisible overlays on the edges of the screen to block unintentional touches. It’s particularly useful for preventing accidental input during gaming, video playback, or reading.

* * *

✨ Features
----------

*   🔒 Blocks touch input on the **left** and **right** edges of the screen
    
*   🔁 Automatically restarts on boot
    
*   🛡️ Runs as a **foreground service** to ensure persistence
    
*   ⚙️ Supports dynamic configuration of blocker width
    
*   📱 Simple UI with one-tap start
    
*   🔍 Optional customization of overlay color for debugging
    

* * *

📂 Project Structure
--------------------

*   `MainActivity.kt`: Simple UI to start the blocker service.
    
*   `TouchBlockerService.kt`: Foreground service that adds overlays to block edge touches.
    
*   `BootReceiver.kt`: Starts the service automatically on boot.
    
*   `activity_main.xml`: Contains a single button to start the service.
    
*   `AndroidManifest.xml`: Declares necessary permissions and components.
    

* * *

🧪 Permissions Required
-----------------------

*   `SYSTEM_ALERT_WINDOW`: Required to draw overlays on the screen.
    
*   `FOREGROUND_SERVICE`: Required to run a persistent background service.
    
*   `RECEIVE_BOOT_COMPLETED`: To restart the blocker after reboot.
    
*   `SCHEDULE_EXACT_ALARM`, `FOREGROUND_SERVICE_SYSTEM_EXEMPTED`: Optional support for future scheduled behavior.
    

* * *

🚀 How to Use
-------------

1.  **Install the app on your Android device.**
    
2.  **Launch the app** and tap **"Start Touch Blocker"**.
    
3.  If prompted, **grant "Draw over other apps" permission**.
    
4.  The app will create invisible overlays along the screen edges.
    
5.  To stop the service, you can kill the app or extend the UI with a Stop button.
    

* * *

🔧 Customization
----------------

You can modify the following parameters in `TouchBlockerService.kt`:

*   `DEFAULT_BLOCKER_WIDTH`: Default width of the edge blockers (in pixels).
    
*   `setBackgroundColor(...)`: Change the overlay color or make it fully transparent for production use.
    

* * *

📦 Boot Behavior
----------------

The app includes a `BootReceiver` that listens for `BOOT_COMPLETED` to automatically restart the blocker service after a device reboot (make sure the user has launched the app at least once and granted necessary permissions).

* * *

⚠️ Notes
--------

*   Only works on Android **Marshmallow (API 23)** and above (due to overlay permissions).
    
*   On **Android 10+**, make sure battery optimizations are disabled for reliable background service.
    
*   For transparency, consider informing users when overlays are active.
    

* * *

🧰 Future Improvements
----------------------

*   UI toggle to stop service
    
*   Option to select which side(s) to block
    
*   Configurable blocker width and color via UI
    
*   Notification actions for enable/disable
    

* * *

🛠️ Build & Run
---------------

1.  Clone the repo
    
2.  Open in Android Studio
    
3.  Build and run on a device/emulator (with overlay support)
    

* * *

📄 License
----------

MIT License (or specify your preferred one)

* * *

Let me know if you'd like a Markdown file version of this or want it updated with GIFs/screenshots.

4o

##### You said:

also provide install instructions for developers using android studio or adb, an apkwill be provided in the directory

###### ChatGPT said:

Absolutely! Here's an updated version of the `README.md` with clear install instructions for both **Android Studio users** and **ADB users**, assuming you're including an `.apk` file in the root directory of your project.

* * *

📱 Screen Touch Blocker
=======================

**Screen Touch Blocker** is a lightweight Android utility that creates invisible overlays on the sides of the screen to block unwanted touches — perfect for gaming, video playback, or reading apps.

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
    
    `git clone https://github.com/your-username/screen-touch-blocker.git`
    
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

MIT License (or insert your license here)
