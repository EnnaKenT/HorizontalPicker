package com.vber.horizontalpicker.recycler_view

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs
import kotlin.math.min

class PickerLayoutManager(
    context: Context?,
    orientation: Int = RecyclerView.HORIZONTAL,
    reverseLayout: Boolean = false,
    private val onScrollStopListener: (selectedView: View) -> Unit
) : LinearLayoutManager(context, orientation, reverseLayout) {

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
        return if (orientation == HORIZONTAL) {
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
            val child = getChildAt(i)
            val childMid = (getDecoratedLeft(child!!) + getDecoratedRight(child)) / 2.0f
            val scale = 1.0f + -scaleDownBy * min(
                unitScaleDownDist,
                abs(mid - childMid)
            ) / unitScaleDownDist

            with(child) {
                scaleX = scale
                scaleY = scale
                if (changeAlpha) {
                    alpha = scale
                }
            }
        }
    }

    companion object {
        private const val SCALE_DOWN_BY = 0.99f
        private const val SCALE_DOWN_DISTANCE = 0.8f
    }

}