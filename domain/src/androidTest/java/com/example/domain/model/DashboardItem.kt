package com.example.domain.model

sealed class DashboardItem {
    data class StepsItem(val stepsCount: Int): DashboardItem()
    data class WaterItem(var waterCount: Int): DashboardItem()
    data class WorkoutItem(val workoutCounts: Int): DashboardItem()
    data class TrainingListItem(val placeholder: Any? = null) : DashboardItem()
}