package com.banuba.sdk.example.effect_player_arcloud_example

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast


fun Context.requireAllPermissionsGranted(
        permissions: Array<String>,
        results: IntArray): Boolean {
    permissions.forEachIndexed { index, permission ->
        if (permission == Manifest.permission.WRITE_EXTERNAL_STORAGE) {
            return@forEachIndexed
        }

        if (results.getOrNull(index) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(
                applicationContext,
                "Not all permissions granted. Please grant $permission permission.",
                Toast.LENGTH_LONG
            ).show()
            return false
        }
    }
    return true
}
