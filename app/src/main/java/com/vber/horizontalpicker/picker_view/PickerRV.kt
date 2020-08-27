package com.vber.horizontalpicker.picker_view

import android.content.Context
import android.util.AttributeSet
import androidx.core.view.doOnLayout
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView

class PickerRV @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    init {
        clipToPadding = false
        //for fixed step selection
        LinearSnapHelper().apply { attachToRecyclerView(this@PickerRV) }
        doOnLayout { widthReceived() }
    }

    private fun widthReceived() {
        updatePadding(left = width / 2, right = width / 2)
        smoothScrollToPosition(0)
    }

}