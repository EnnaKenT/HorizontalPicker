package com.vber.horizontalpicker.recycler_view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView

class PickerRV @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    init {
        LinearSnapHelper().apply { attachToRecyclerView(this@PickerRV) }
    }
}