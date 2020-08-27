package com.vber.horizontalpicker.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

fun Context.showToast(@StringRes textRes: Int) = showToast(getString(textRes))
fun Context.showToast(text: CharSequence) = showToast(text.toString())
fun Context.showToast(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

fun Context.getCompatColor(@ColorRes colorRes: Int) = ContextCompat.getColor(this, colorRes)

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View =
    this.context.inflate().inflate(layoutRes, this, attachToRoot)

fun Context.inflate(): LayoutInflater = LayoutInflater.from(this)