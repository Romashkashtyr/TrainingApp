package com.example.trainings.domain.usecases


import com.example.trainings.data.response.FullExercise
import com.example.trainings.domain.TrainingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveFavoriteExercisesUseCase @Inject constructor(
    private val repository: TrainingsRepository
) {

//    suspend operator fun invoke(): List<FullExercise> {
//        return repository.getFavoriteExercises()
//    }

    operator fun invoke(): Flow<List<FullExercise>> {
        return repository.observeFavoriteExercises()
    }

}