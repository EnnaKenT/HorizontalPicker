package com.vber.horizontalpicker.util

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.showToast(@StringRes textRes: Int) = showToast(getString(textRes))
fun Context.showToast(text: CharSequence) = showToast(text.toString())
fun Context.showToast(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()