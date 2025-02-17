package com.example.trainingapp.presentation.base.main.rc_view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingapp.R
import com.example.trainingapp.databinding.ItemStepsBinding
import com.example.trainingapp.databinding.ItemTrainingListBinding
import com.example.trainingapp.databinding.ItemWaterBinding
import com.example.trainingapp.databinding.ItemWorkoutBinding
import com.example.trainingapp.domain.DashboardItem

sealed class DashboardViewHolder(view: View) : RecyclerView.ViewHolder(view) {


    abstract fun bind(item: DashboardItem)

     class StepsViewHolder(private val binding: ItemStepsBinding) : DashboardViewHolder(binding.root) {
        override fun bind(item: DashboardItem){
            binding.stepsCount.text = String.format((item as DashboardItem.StepsItem).stepsCount.toString())
        }
    }

    class WaterViewHolder(
        private val binding: ItemWaterBinding,
        private val onAddWaterClicked: (Int) -> Unit
    ) : DashboardViewHolder(binding.root){
        override fun bind(item: DashboardItem){
            binding.waterIntake.text = String.format((item as DashboardItem.WaterItem).waterCount.toString())
            binding.addWaterButton.setOnClickListener {
                item.waterCount += 100
                onAddWaterClicked(item.waterCount)
                binding.waterIntake.text = item.waterCount.toString()
            }
        }
    }

     class WorkoutsViewHolder(private val binding: ItemWorkoutBinding) : DashboardViewHolder(binding.root){
        override fun bind(item: DashboardItem){
            binding.workoutsCount.text = String.format((item as DashboardItem.WorkoutItem).workoutCounts.toString())
        }
    }

     class TrainingListViewHolder(
        private val binding: ItemTrainingListBinding,
        private val onViewTrainingsClicked: () -> Unit
    ) : DashboardViewHolder(binding.root){
        override fun bind(item: DashboardItem){
            binding.viewWorkoutsButton.setText(R.string.trains_county)
            binding.viewWorkoutsButton.setOnClickListener { onViewTrainingsClicked() }
        }
    }



}