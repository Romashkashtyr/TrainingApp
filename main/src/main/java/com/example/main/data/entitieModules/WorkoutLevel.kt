package com.example.main.data.entitieModules

enum class WorkoutLevel(
    val exerciseDuration: Int,
    val restDuration: Int
) {

    EASY(
        exerciseDuration = 20,
        restDuration = 15
    ),
    MEDIUM(
        exerciseDuration = 30,
        restDuration = 10
    ),
    HARD(
        exerciseDuration = 45,
        restDuration = 5
    )
}