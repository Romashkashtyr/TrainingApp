package com.example.trainingapp.presentation.main.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingapp.databinding.ItemTrainingListBinding
import com.example.trainingapp.domain.DashboardItem
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.example.trainingapp.R
import com.example.trainingapp.domain.events.OnTrainingClick

class TrainingListItemDelegate(
    private val onTrainingClick: OnTrainingClick
): AdapterDelegate<List<DashboardItem>>() {
    override fun isForViewType(items: List<DashboardItem>, position: Int): Boolean {
        return items[position] is DashboardItem.TrainingListItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return TrainingListViewHolder(
            ItemTrainingListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onTrainingClick = onTrainingClick::onTrainingClick,
        )
    }

    override fun onBindViewHolder(
        items: List<DashboardItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as TrainingListViewHolder).bind(items[position] as DashboardItem.TrainingListItem)
    }

    inner class TrainingListViewHolder(
        private val binding: ItemTrainingListBinding,
        private val onTrainingClick: () -> Unit
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DashboardItem.TrainingListItem) {
            binding.viewWorkoutsButton.setText(R.string.training_list)
            binding.viewWorkoutsButton.setOnClickListener {
                onTrainingClick
            }
        }
    }

}