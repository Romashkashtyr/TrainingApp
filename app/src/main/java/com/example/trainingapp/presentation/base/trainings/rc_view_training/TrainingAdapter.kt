package com.example.trainingapp.presentation.base.trainings.rc_view_training

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingapp.R
import com.example.trainingapp.databinding.ItemTrainingBinding
import com.example.trainingapp.domain.Training

class TrainingAdapter(private val trainingsList: List<Training>) :
    RecyclerView.Adapter<TrainingAdapter.TrainingViewHolder>() {

    inner class TrainingViewHolder(private val binding: ItemTrainingBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Training) {
            binding.trainingName.text = item.trainingName
            binding.trainingDuration.text = item.duration.toIsoString()
            binding.trainingComplexity.text = when (item.complexity) {
                0 -> context.getString(R.string.level_easy)
                1 -> context.getString(R.string.level_medium)
                else -> context.getString(R.string.level_hard)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingViewHolder {
        val binding = ItemTrainingBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return TrainingViewHolder(binding, parent.context)
    }

    override fun getItemCount(): Int {
        return trainingsList.size
    }

    override fun onBindViewHolder(holder: TrainingViewHolder, position: Int) {
        return holder.bind(trainingsList[position])
    }
}