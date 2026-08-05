package com.example.main.ui.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.main.structures.DashboardItem
import com.example.main.R
import com.example.main.databinding.ItemWaterBinding
import com.example.main.ui.OnAddWaterClicked
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WaterItemDelegate(
    private val onAddWaterClicked: OnAddWaterClicked
) : AdapterDelegate<List<DashboardItem>>() {
    override fun isForViewType(items: List<DashboardItem>, position: Int): Boolean {
        return items[position] is DashboardItem.WaterItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return WaterViewHolder(
            ItemWaterBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onAddWaterClicked = onAddWaterClicked::onAddWaterClicked,
            onWaterItemClick = onAddWaterClicked::onWaterItemClick
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
        private val onAddWaterClicked: (Int) -> (Unit),
        private val onWaterItemClick: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DashboardItem.WaterItem) {
            binding.waterIntake.text = item.waterCount.toString()
            binding.root.setOnClickListener {
                onWaterItemClick()
            }
            binding.addWaterButton.setOnClickListener {
                val newAmount = binding.waterInputEditText.text.toString().toIntOrNull() ?: 0
                if (newAmount > 0) {
                    onAddWaterClicked(newAmount)
                    binding.waterInputEditText.text?.clear()
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