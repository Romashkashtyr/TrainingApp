package com.example.main.ui.workout_running

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.core.base.BaseFragment
import com.example.core.navigation.Router
import com.example.main.data.entitieModules.WorkoutExercise
import com.example.main.data.entitieModules.WorkoutLevel
import com.example.main.data.entitieModules.WorkoutType
import com.example.main.databinding.FragmentWorkoutRunningBinding
import com.example.main.di.MainComponent
import javax.inject.Inject

class WorkoutRunningFragment: BaseFragment(), WorkoutRunningView {

    private var _binding: FragmentWorkoutRunningBinding? = null

    private val binding: FragmentWorkoutRunningBinding
        get() = _binding!!

    @Inject
    lateinit var presenter: WorkoutRunningPresenter

    private lateinit var level: WorkoutLevel
    private lateinit var type: WorkoutType

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

        MainComponent
            .getMainInstance()
            .inject(this)

        super.onCreate(savedInstanceState)

        level = WorkoutLevel.valueOf(
            requireArguments().getString(ARG_LEVEL) ?: WorkoutLevel.EASY.name
        )

        type = WorkoutType.valueOf(
            requireArguments().getString(ARG_TYPE) ?: WorkoutType.FULL_BODY.name
        )

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding =
            FragmentWorkoutRunningBinding.inflate(
                inflater,
                container,
                false
            )

        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {

        super.onViewCreated(
            view,
            savedInstanceState
        )

       binding.arrowBack.setOnClickListener {
           presenter.stopWorkout()

           requireActivity()
               .onBackPressedDispatcher
               .onBackPressed()
       }

        binding.nextButton.setOnClickListener {
            presenter.nextExercise()
        }

        presenter.loadWorkout(
            type = type,
            level = level
        )
    }


    override fun showExercise(
        exercise: WorkoutExercise,
        position: Int,
        total: Int
    ) {

        binding.exerciseCounter.text =
            "${position + 1} / $total"

        binding.exerciseName.text =
            exercise.exerciseName

        binding.timer.text =
            exercise.durationSeconds.toString()

        Glide.with(this)
            .load(exercise.imageUrl)
            .into(binding.exerciseImage)
    }

    override fun updateTimer(
        seconds: Int
    ) {

        binding.timer.text =
            seconds.toString()
    }

    override fun showWorkoutFinished() {

        binding.timer.text = "Готово!"

        binding.nextButton.text =
            "Завершить"

        binding.nextButton.setOnClickListener {

            requireActivity()
                .onBackPressedDispatcher
                .onBackPressed()
        }
    }

    override fun showError(
        message: String
    ) {

        Toast.makeText(
            requireContext(),
            message,
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onDestroyView() {

        presenter.stopWorkout()

        _binding = null

        super.onDestroyView()
    }

    companion object {

        private const val ARG_LEVEL =
            "workout_level"

        private const val ARG_TYPE =
            "workout_type"

        fun newInstance(
            level: WorkoutLevel,
            type: WorkoutType
        ): WorkoutRunningFragment {

            return WorkoutRunningFragment().apply {

                arguments = Bundle().apply {

                    putString(
                        ARG_LEVEL,
                        level.name
                    )

                    putString(
                        ARG_TYPE,
                        type.name
                    )
                }
            }
        }
    }
}