package org.lotka.xenonx.domain.util.util

import android.os.Build

fun storagePermissions(): Array<String> {
    return when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
            arrayOf(android.Manifest.permission.READ_MEDIA_IMAGES)
        }

        else -> {
            arrayOf(
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        }
    }
}

fun cameraPermissions(): String {
    return android.Manifest.permission.CAMERA
}
