package com.vber.horizontalpicker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vber.horizontalpicker.R

class PickerAdapter(private val itemClickedAction: (position: Int) -> Unit) :
    RecyclerView.Adapter<PickerVH>() {

    private var dataList: List<String>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PickerVH {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.picker_item_layout, parent, false)
        return PickerVH(view, itemClickedAction)
    }

    override fun onBindViewHolder(holder: PickerVH, position: Int) = holder.bind(dataList!![position])

    override fun getItemCount(): Int = dataList!!.size

    fun setItems(newData: List<String>) {
        dataList = newData
        notifyDataSetChanged()
    }

}