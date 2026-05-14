package com.example.trainings.ui.rc_view_training


import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.trainings.R
import com.example.trainings.data.response.ExerciseUi
import com.example.trainings.databinding.ItemTrainingBinding
import okhttp3.internal.notify

class TrainingAdapter : ListAdapter<ExerciseUi, TrainingAdapter.TrainingViewHolder>(DiffCallback()) {

    private var fullList = listOf<ExerciseUi>()

    private var expandedItems = mutableSetOf<String>()

    inner class TrainingViewHolder(
        private val binding: ItemTrainingBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ExerciseUi) {
            binding.trainingName.text = item.name ?: "No name"
            binding.trainingDescription.text = item.description
            binding.musclesGroupName.text = item.primaryMuscles.joinToString { it.name }


            binding.imageProgress.visibility = View.VISIBLE

            Glide.with(binding.root.context)
                .load(item.imageUrl)
                .into(binding.exerciseImage)


            val isExpanded = expandedItems.contains(item.id)

            if (isExpanded) {
                binding.trainingDescription.maxLines = Int.MAX_VALUE
                binding.trainingDescription.ellipsize = null
                binding.toggleDescription.setText(R.string.hide)
            } else {
                binding.trainingDescription.maxLines = 3
                binding.trainingDescription.ellipsize = TextUtils.TruncateAt.END
                binding.toggleDescription.setText(R.string.show_more)
            }

            binding.toggleDescription.setOnClickListener {
                if (expandedItems.contains(item.id)) {
                    expandedItems.remove(item.id)
                } else {
                    expandedItems.add(item.id)
                }

                notifyItemChanged(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingViewHolder {
        val binding = ItemTrainingBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return TrainingViewHolder(binding)
    }


    override fun onBindViewHolder(holder: TrainingViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }

    fun updateList(newItems: List<ExerciseUi>) {
        fullList = newItems

        submitList(newItems)
    }

    fun filterList(query: String) {
        if (query.isBlank()) {
            submitList(fullList)
        } else {
            val filtered = fullList.filter {
                it.name?.contains(query, ignoreCase = true) == true ||
                        it.primaryMuscles.any {muscle ->
                            muscle.name.contains(query, ignoreCase = true)
                        }
            }
            submitList(filtered)
        }

    }


}