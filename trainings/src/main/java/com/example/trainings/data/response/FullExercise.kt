package com.example.trainings.data.response

data class FullExercise(
    override val id: String,
    override val name: String?,
    override val description: String?,
    override val primaryMuscles: List<PrimaryMuscles>,
    val imageUrl: String,
) : ExerciseInterface {

    private constructor(own: ExerciseInterface, image: String): this(
        id = own.id,
        name = own.name,
        description = own.description,
        primaryMuscles = own.primaryMuscles,
        imageUrl = image,
    )

    companion object {
        fun ExerciseInterface.toFullExercise(image: String): FullExercise {
            return FullExercise(this, image)
        }
    }
}