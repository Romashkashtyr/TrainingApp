package com.example.trainingapp.presentation.base.main.rc_view_training

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingapp.databinding.TrainingsLayoutItemBinding
import com.example.trainingapp.domain.TrainingName

class TrainingViewHolder(private val binding: TrainingsLayoutItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: TrainingName){
        binding.trainingName.text = item.trainingName
        binding.trainingDuration.text = item.duration
        binding.trainingComplexity.text = item.complexity
    }

}