package com.example.trainingapp.presentation.base.trainings.rc_view_training

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingapp.databinding.ItemTrainingsBinding
import com.example.trainingapp.domain.Training

class TrainingAdapter(private val trainingsList: ArrayList<Training>) :
    RecyclerView.Adapter<TrainingAdapter.TrainingViewHolder>() {

    inner class TrainingViewHolder(private val binding: ItemTrainingsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Training) {
            binding.trainingName.text = item.trainingName
            binding.trainingDuration.text = item.duration.toIsoString()
            binding.trainingComplexity.text = when (item.complexity) {
                0 -> "Легкий"
                1 -> "Средний"
                else -> "Тяжелый"
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingViewHolder {
        val binding = ItemTrainingsBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return TrainingViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return trainingsList.size
    }

    override fun onBindViewHolder(holder: TrainingViewHolder, position: Int) {
        return holder.bind(trainingsList[position])
    }
}