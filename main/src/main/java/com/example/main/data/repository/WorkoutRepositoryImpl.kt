package com.example.main.data.repository

import com.example.core.providers.ExercisesProvider
import com.example.database.TrainingRoomDatabase
import com.example.main.data.MainMapper.toWorkoutHistory
import com.example.main.data.entitieModules.WorkoutExercise
import com.example.main.data.entitieModules.WorkoutHistory
import com.example.main.data.entitieModules.WorkoutLevel
import com.example.main.data.entitieModules.WorkoutType
import com.example.main.domain.repository.WorkoutRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WorkoutRepositoryImpl @Inject constructor(
    private val exercisesProvider: ExercisesProvider,
    private val database: TrainingRoomDatabase
): WorkoutRepository {
    override suspend fun getWorkout(
        type: WorkoutType,
        level: WorkoutLevel
    ): List<WorkoutExercise> {
        val exercises = exercisesProvider.getExercises()

        val selectedExercises = when (type) {
            WorkoutType.FULL_BODY -> {
                exercises.shuffled().take(8)
            }

            WorkoutType.UPPER_BODY -> {
                exercises.filter { exercise ->
                    exercise.muscleNames.any { muscle ->
                        muscle.contains("chest", ignoreCase = true) ||
                                muscle.contains("shoulder", ignoreCase = true) ||
                                muscle.contains("biceps", ignoreCase = true) ||
                                muscle.contains("triceps", ignoreCase = true) ||
                                muscle.contains("back", ignoreCase = true)
                    }
                }
                    .shuffled()
                    .take(8)
            }

            WorkoutType.LOWER_BODY -> {
                exercises.filter { exercise ->
                    exercise.muscleNames.any { muscle ->
                        muscle.contains("quadriceps", ignoreCase = true) ||
                                muscle.contains("hamstrings", ignoreCase = true) ||
                                muscle.contains("glutes", ignoreCase = true) ||
                                muscle.contains("calves", ignoreCase = true) ||
                                muscle.contains("legs", ignoreCase = true)
                    }
                }
                    .shuffled()
                    .take(8)
            }

            else -> {
                exercises.filter { exercise ->
                    exercise.muscleNames.any { muscle ->
                        muscle.contains(type.name, ignoreCase = true)
                    }
                }
                    .shuffled()
                    .take(8)
            }
        }


        return selectedExercises.map { exercise ->
            WorkoutExercise(
                exerciseId = exercise.id,
                exerciseName = exercise.name,
                description = exercise.description,
                imageUrl = exercise.imageUrl,
                durationSeconds = level.exerciseDuration
            )
        }
    }

    override fun observeHistory(): Flow<List<WorkoutHistory>> {
        return database.workoutDao().observeHistory()
            .map { history ->
                history.map { entity ->
                    entity.toWorkoutHistory()
                }
            }
    }
}


