package com.example.main.ui.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingapp.R
import com.example.trainingapp.databinding.ItemWaterBinding
import com.example.trainingapp.domain.DashboardItem
import com.example.trainingapp.domain.events.OnAddWaterClicked
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate

class WaterItemDelegate(
    private val onAddWaterClicked: OnAddWaterClicked
): AdapterDelegate<List<DashboardItem>>() {
    override fun isForViewType(items: List<DashboardItem>, position: Int): Boolean {
        return items[position] is DashboardItem.WaterItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return WaterViewHolder(
            ItemWaterBinding.inflate(LayoutInflater.from(parent.context),parent, false),
            onAddWaterClicked = onAddWaterClicked::onAddWaterClicked
        )
    }

    override fun onBindViewHolder(
        items: List<DashboardItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as WaterViewHolder).bind(items[position] as DashboardItem.WaterItem)
    }

    inner class WaterViewHolder(
        private val binding: ItemWaterBinding,
        private val onAddWaterClicked: (Int) -> (Unit)
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DashboardItem.WaterItem) {
            binding.waterIntake.text = item.waterCount.toString()
            binding.addWaterButton.setOnClickListener {
                val newAmount = binding.waterInputEditText.text.toString().toIntOrNull() ?: 0
                if (newAmount > 0 ){
                    onAddWaterClicked(newAmount)
                } else {
                    Toast.makeText(
                        binding.root.context,
                        binding.root.context.getString(R.string.invalid_amount),
                        Toast.LENGTH_LONG
                        ).show()
                }

            }
        }
    }
}