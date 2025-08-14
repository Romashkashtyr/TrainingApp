package com.example.trainingapp.presentation.main.delegates

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingapp.databinding.ItemStepsBinding
import com.example.trainingapp.domain.DashboardItem
import com.example.trainingapp.presentation.main.rc_view.DashboardViewHolder
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

class StepsItemDelegate : AdapterDelegate<List<DashboardItem>>(){


    override fun isForViewType(items: List<DashboardItem>, position: Int): Boolean {
        return items[position] is DashboardItem.StepsItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return DashboardViewHolder.StepsViewHolder(
            ItemStepsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(
        items: List<DashboardItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as DashboardViewHolder.StepsViewHolder).bind(items[position] as DashboardItem.StepsItem)
    }

    inner class StepsViewHolder(
        private val binding: ItemStepsBinding
    ): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: DashboardItem.StepsItem){
            binding.stepsCount.text = item.stepsCount.toString()
        }
    }

}