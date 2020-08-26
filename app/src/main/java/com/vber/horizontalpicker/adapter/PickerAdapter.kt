package com.vber.horizontalpicker.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vber.horizontalpicker.R
import com.vber.horizontalpicker.util.inflate

class PickerAdapter(private val itemClickedAction: (position: Int) -> Unit) :
    RecyclerView.Adapter<PickerVH>() {

    private var dataList: List<String>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PickerVH {
        val view = parent.inflate(R.layout.picker_item_layout)
        return PickerVH(view, itemClickedAction)
    }

    override fun onBindViewHolder(holder: PickerVH, position: Int) =
        holder.bind(dataList!![position])

    override fun getItemCount(): Int = dataList!!.size

    fun setItems(newData: List<String>) {
        dataList = newData
        notifyDataSetChanged()
    }

}