package com.example.trainings.ui

import com.google.common.collect.ImmutableList

sealed class TrainingState(open val trainingData : ImmutableList<TrainingUI>?) {

    data object None: TrainingState(trainingData = null)

    class Loading(trainingData: ImmutableList<TrainingUI>? = null): TrainingState(trainingData)

    class Error(trainingData: ImmutableList<TrainingUI>? = null) :TrainingState(trainingData)

    class Success(override val trainingData: ImmutableList<TrainingUI>) : TrainingState(trainingData)
}