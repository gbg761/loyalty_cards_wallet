package com.example.loyaltycardwallet.utils

import android.app.Activity
import android.graphics.Point
import android.graphics.Rect
import android.os.Build
import android.view.Display

fun Activity.getScreenWidth(): Int {
    return if (Build.VERSION.SDK_INT > Build.VERSION_CODES.R) {
        val rect: Rect = windowManager.currentWindowMetrics.bounds
        rect.right
    } else {
        @Suppress("DEPRECATION")
        val display: Display = windowManager.defaultDisplay
        val size = Point()
        @Suppress("DEPRECATION")
        display.getSize(size)
        size.x
    }
}