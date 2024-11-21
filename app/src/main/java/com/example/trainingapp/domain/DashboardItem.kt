package com.example.trainingapp.domain

sealed class DashboardItem {
    data class StepsItem(val stepsCount: Int): DashboardItem()
    data class WaterItem(val waterCount: Int): DashboardItem()
    data class WorkoutItem(val workoutCounts: Int): DashboardItem()
    data object TrainingListItem : DashboardItem()
}