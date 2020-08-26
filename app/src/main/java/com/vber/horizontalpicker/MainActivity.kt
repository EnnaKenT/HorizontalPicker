package com.vber.horizontalpicker

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.vber.horizontalpicker.adapter.PickerAdapter
import com.vber.horizontalpicker.recycler_view.PickerLayoutManager
import com.vber.horizontalpicker.util.showToast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val adapter: PickerAdapter by lazy {
        PickerAdapter(::pickerAdapterClicked).apply {
            setItems(getData())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val manager = PickerLayoutManager(this, onScrollStopListener = ::pickerLayoutScrollStopped)
        with(rv) {
            layoutManager = manager
            adapter = this@MainActivity.adapter
        }
    }

    private fun pickerAdapterClicked(position: Int) = rv.smoothScrollToPosition(position)

    private fun pickerLayoutScrollStopped(selectedView: View) =
        showToast(getString(R.string.pickerToast, (selectedView as TextView).text))

    private fun getData(): List<String> = generateSequence(0) { it + 1 }
        .take(PICKER_COUNT)
        .map { it.toString() }
        .toList()

    companion object {
        private const val PICKER_COUNT = 100
    }

}