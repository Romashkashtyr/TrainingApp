package com.example.trainingapp.presentation.base.main_logic.rc_view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingapp.databinding.ItemStepsBinding
import com.example.trainingapp.databinding.ItemTrainingListBinding
import com.example.trainingapp.databinding.ItemWaterBinding
import com.example.trainingapp.databinding.ItemWorkoutBinding
import com.example.trainingapp.domain.DashboardItem

sealed class DashboardViewHolder(view: View) : RecyclerView.ViewHolder(view) {

     class StepsViewHolder(private val binding: ItemStepsBinding) : DashboardViewHolder(binding.root) {
        fun bind(item: DashboardItem.StepsItem){
            binding.stepsCount.text = item.stepsCount.toString()
        }
    }

    class WaterViewHolder(
        private val binding: ItemWaterBinding,
        private val onAddWaterClicked: () -> Unit
    ) : DashboardViewHolder(binding.root){
        fun bind(item: DashboardItem.WaterItem){
            binding.waterIntake.text = item.waterCount.toString()
            binding.addWaterButton.setOnClickListener { onAddWaterClicked() }
        }
    }

     class WorkoutsViewHolder(private val binding: ItemWorkoutBinding) : DashboardViewHolder(binding.root){
        fun bind(item: DashboardItem.WorkoutItem){
            binding.workoutsCount.text = item.workoutCounts.toString()
        }
    }

     class TrainingListViewHolder(
        private val binding: ItemTrainingListBinding,
        private val onViewTrainingsClicked: () -> Unit
    ) : DashboardViewHolder(binding.root){
        fun bind(item: DashboardItem.TrainingListItem){
            binding.viewWorkoutsButton.text = item.trainingList.toString()
            binding.viewWorkoutsButton.setOnClickListener { onViewTrainingsClicked() }
        }
    }
}