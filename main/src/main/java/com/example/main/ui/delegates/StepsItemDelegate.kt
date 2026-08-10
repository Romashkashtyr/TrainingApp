package com.example.main.ui.delegates

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.main.structures.DashboardItem
import com.example.main.databinding.ItemStepsBinding
import com.example.main.ui.OnStepsClick
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate

class StepsItemDelegate(
    private val onStepsClick: OnStepsClick
) : AdapterDelegate<List<DashboardItem>>() {


    override fun isForViewType(items: List<DashboardItem>, position: Int): Boolean {
        return items[position] is DashboardItem.StepsItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return StepsViewHolder(
            ItemStepsBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onStepsItemClick = onStepsClick::onStepsClick
        )
    }

    override fun onBindViewHolder(
        items: List<DashboardItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as StepsViewHolder).bind(items[position] as DashboardItem.StepsItem)
    }

    inner class StepsViewHolder(
        private val binding: ItemStepsBinding,
        private val onStepsItemClick: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: DashboardItem.StepsItem) {
            binding.stepsCount.text = item.stepsCount.toString()
            binding.root.setOnClickListener {
                onStepsItemClick()
            }
        }
    }

}