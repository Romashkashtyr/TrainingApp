package com.example.trainingapp.presentation.base.main.rc_view_training

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingapp.R
import com.example.trainingapp.databinding.TrainingsLayoutItemBinding
import com.example.trainingapp.domain.TrainingName

class TrainingAdapter(private val trainingsList: ArrayList<TrainingName>): RecyclerView.Adapter<TrainingViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingViewHolder {
        val binding = TrainingsLayoutItemBinding
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