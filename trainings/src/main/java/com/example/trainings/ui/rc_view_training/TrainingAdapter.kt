package com.example.trainings.ui.rc_view_training

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.trainings.data.response.Exercise
import com.example.trainings.databinding.ItemTrainingBinding

class TrainingAdapter(
//private val trainingsList: MutableList<Exercise> = mutableListOf()//
) :
    RecyclerView.Adapter<TrainingAdapter.TrainingViewHolder>() {

    private val fullList = mutableListOf<Exercise>()
    //val fullList: List<Exercise> = _fullList.toList()

    private val filteredList = mutableListOf<Exercise>()
    //val filteredList = _filteredList.toList()

    inner class TrainingViewHolder(
        private val binding: ItemTrainingBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Exercise) {
            binding.trainingName.text = item.name ?: "No name"
            binding.trainingDescription.text = item.description
            binding.musclesGroupName.text = item.primaryMuscles.joinToString { it.name }

            val imageUrl = "https://api.workoutapi.com/exercises/${item.id}/image"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingViewHolder {
        val binding = ItemTrainingBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return TrainingViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    override fun onBindViewHolder(holder: TrainingViewHolder, position: Int) {
        return holder.bind(filteredList[position])
    }

    fun updateList(newItems: List<Exercise>) {
        fullList.clear()
        fullList.addAll(newItems)

        filteredList.clear()
        filteredList.addAll(newItems)

       // trainingsList.clear()
        //trainingsList.addAll(newItems)
        notifyItemChanged(newItems.size)
    }

    fun filterList(query: String) {
        val resultList = if (query.isBlank()) {
            fullList
        } else {
            fullList.filter {
                it.name?.contains(query, ignoreCase = true) == true ||
                        it.primaryMuscles.any {muscle ->
                            muscle.name.contains(query, ignoreCase = true)
                        }
            }
        }
        filteredList.clear()
        filteredList.addAll(resultList)
    }


}