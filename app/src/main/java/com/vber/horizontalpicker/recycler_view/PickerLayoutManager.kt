package com.vber.horizontalpicker.recycler_view

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vber.horizontalpicker.R
import com.vber.horizontalpicker.util.getCompatColor
import com.vber.horizontalpicker.util.initGradientColor
import kotlin.math.abs
import kotlin.math.min

class PickerLayoutManager(
    context: Context,
    orientation: Int = RecyclerView.HORIZONTAL,
    reverseLayout: Boolean = false,
    private val onScrollStopListener: (selectedView: View) -> Unit
) : LinearLayoutManager(context, orientation, reverseLayout) {

    private val blueColor = context.getCompatColor(R.color.colorTextRecyclerCenter)
    private val grayColor = context.getCompatColor(R.color.colorTextRecyclerStartEnd)

    var scaleDownBy = SCALE_DOWN_BY
    var scaleDownDistance = SCALE_DOWN_DISTANCE
    var changeAlpha = true

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        super.onLayoutChildren(recycler, state)
        scaleDownView()
    }

    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        return if (orientation == RecyclerView.HORIZONTAL) {
            val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
            scaleDownView()
            scrolled
        } else {
            0
        }
    }

    override fun onScrollStateChanged(state: Int) {
        super.onScrollStateChanged(state)
        if (state == RecyclerView.SCROLL_STATE_IDLE) {
            var selected = 0
            var lastHeight = 0f
            for (i in 0 until childCount) {
                if (lastHeight < getChildAt(i)!!.scaleY) {
                    lastHeight = getChildAt(i)!!.scaleY
                    selected = i
                }
            }
            onScrollStopListener(getChildAt(selected)!!)
        }
    }

    private fun scaleDownView() {
        val mid = width / 2.0f
        val unitScaleDownDist = scaleDownDistance * mid
        for (i in 0 until childCount) {
            val child = getChildAt(i) as TextView
            val childMid = (getDecoratedLeft(child) + getDecoratedRight(child)) / 2.0f
            val coef = min(unitScaleDownDist, abs(mid - childMid))
            val scale = 1.0f + (-scaleDownBy * coef / unitScaleDownDist)
            child.scaleX = scale
            child.scaleY = scale

            when {
                scale >= SCALE_TO_SHOW_GRADIENT -> setupGradientColor(child)
                scale < SCALE_TO_HIDE_VIEW -> resetColorAndAlpha(child)
                else -> setupDefaultColor(child)
            }
        }
    }

    private fun setupGradientColor(textView: TextView) {
        if (changeAlpha) textView.alpha = 1.0F
        textView.setTextColor(blueColor)
        textView.initGradientColor()
    }

    private fun resetColorAndAlpha(textView: TextView) {
        if (changeAlpha) textView.alpha = 0F
        textView.paint.shader = null
        textView.setTextColor(grayColor)
    }

    private fun setupDefaultColor(textView: TextView) {
        if (changeAlpha) textView.alpha = 1.0F
        textView.paint.shader = null
        textView.setTextColor(grayColor)
    }

    companion object {
        private const val SCALE_DOWN_BY = 0.99f
        private const val SCALE_DOWN_DISTANCE = 0.8f

        private const val SCALE_TO_SHOW_GRADIENT = 0.95F
        private const val SCALE_TO_HIDE_VIEW = 0.4F
    }

}