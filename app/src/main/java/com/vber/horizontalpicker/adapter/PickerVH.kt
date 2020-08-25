package com.vber.horizontalpicker.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.picker_item_layout.view.*

class PickerVH(itemView: View, itemClickedAction: (position: Int) -> Unit) :
    RecyclerView.ViewHolder(itemView) {

    init {
        with(itemView) {
            picker_item.setOnClickListener { itemClickedAction(adapterPosition) }
        }
    }

    fun bind(item: String) = with(itemView) {
        picker_item.text = item
    }
}