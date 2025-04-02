package com.example.screentouchblocker

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import android.app.AlertDialog

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScreenTouchBlockerApp(this)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            packageManager.setComponentEnabledSetting(
                ComponentName(this, BootReceiver::class.java),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP
            )
        }
    }
}

@Composable
fun ScreenTouchBlockerApp(activity: Activity) {
    var selectedWidth by remember { mutableStateOf(100) } // Default width
    var showDialog by remember { mutableStateOf(false) }  // To trigger the width selection dialog

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                if (!Settings.canDrawOverlays(activity)) {
                    activity.startActivity(Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION))
                    Toast.makeText(activity, "Grant overlay permission!", Toast.LENGTH_SHORT).show()
                } else {
                    val intent = Intent(activity, TouchBlockerService::class.java)
                    intent.putExtra(TouchBlockerService.EXTRA_BLOCKER_WIDTH, selectedWidth)
                    activity.startService(intent)
                }
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Start Blocking")
        }

        Button(
            onClick = {
                activity.stopService(Intent(activity, TouchBlockerService::class.java))
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Stop Blocking")
        }

        Button(
            onClick = {
                showDialog = true // Show the dialog to select width
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Select Blocker Width ($selectedWidth px)")
        }

        // Show the width selection dialog
        if (showDialog) {
            ShowWidthSelectionDialog(
                onDismiss = { showDialog = false },
                onWidthSelected = { selectedWidth = it; showDialog = false }
            )
        }
    }
}

@Composable
fun ShowWidthSelectionDialog(onDismiss: () -> Unit, onWidthSelected: (Int) -> Unit) {
    val widthOptions = listOf(30, 50, 100, 150, 200, 250, 300)

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Select Blocker Width") },
        text = {
            Column {
                widthOptions.forEach { width ->
                    TextButton(onClick = { onWidthSelected(width) }) {
                        Text("$width px")
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
