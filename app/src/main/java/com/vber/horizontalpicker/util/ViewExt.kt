package com.vber.horizontalpicker.util

import android.graphics.LinearGradient
import android.graphics.Shader
import android.widget.TextView
import com.vber.horizontalpicker.R

fun TextView.initGradientColor() {
    val textShader: Shader = LinearGradient(
        0F, 0F, width.toFloat(), 0F, intArrayOf(
            context.getColor(R.color.colorCircleProgressGradientStart),
            context.getColor(R.color.colorCircleProgressGradientEnd)
        ), null, Shader.TileMode.MIRROR
    )
    paint.shader = textShader
}