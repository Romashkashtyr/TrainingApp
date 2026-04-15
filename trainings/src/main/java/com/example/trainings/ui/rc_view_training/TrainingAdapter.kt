package com.example.trainings.ui.rc_view_training

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trainings.data.response.Exercise
import com.example.trainings.databinding.ItemTrainingBinding

class TrainingAdapter(private val trainingsList: MutableList<Exercise> = mutableListOf()) :
    RecyclerView.Adapter<TrainingAdapter.TrainingViewHolder>() {

    inner class TrainingViewHolder(
        private val binding: ItemTrainingBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Exercise) {
            binding.trainingName.text = item.musclesName
            binding.trainingDescription.text = item.description
            binding.musclesGroupName.text = item.primaryMuscles.map {
                it.name
            }.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingViewHolder {
        val binding = ItemTrainingBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return TrainingViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return trainingsList.size
    }

    override fun onBindViewHolder(holder: TrainingViewHolder, position: Int) {
        return holder.bind(trainingsList[position])
    }

    fun updateList(newItems: List<Exercise>) {
        trainingsList.clear()
        trainingsList.addAll(newItems)
        notifyItemChanged(newItems.size)
    }
}