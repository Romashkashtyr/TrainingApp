package com.example.main.ui.workout_fragment



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.core.base.BaseFragment
import com.example.core.navigation.Router
import com.example.core.navigation.RouterHolder
import com.example.core.navigation.Screen
import com.example.main.R
import com.example.main.data.entitieModules.WorkoutLevel
import com.example.main.data.entitieModules.WorkoutType
import com.example.main.databinding.FragmentWorkoutBinding
import com.example.main.di.MainComponent
import javax.inject.Inject

class WorkoutFragment : BaseFragment(), WorkoutView {

    private var _binding: FragmentWorkoutBinding? = null

    private val binding: FragmentWorkoutBinding
        get() = _binding!!

    @Inject
    lateinit var presenter: WorkoutPresenter

    @Inject
    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {

        MainComponent
            .getMainInstance()
            .inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentWorkoutBinding.inflate(
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

        setupViews()
    }

    private fun setupViews() {

        binding.easyRadioButton.isChecked = true

        binding.startWorkoutButton.setOnClickListener {

            val level = when {

                binding.easyRadioButton.isChecked ->
                    WorkoutLevel.EASY

                binding.mediumRadioButton.isChecked ->
                    WorkoutLevel.MEDIUM

                binding.hardRadioButton.isChecked ->
                    WorkoutLevel.HARD

                else ->
                    WorkoutLevel.EASY
            }

            openRunningWorkout(level)
        }

        binding.arrowBack.setOnClickListener {

            requireActivity()
                .onBackPressedDispatcher
                .onBackPressed()
        }
    }

    private fun openRunningWorkout(
        level: WorkoutLevel
    ) {
        router.navigateToFragment(

            screen = Screen.WorkoutRunningFragmentRoute(
                fromContext = requireActivity(),
                containerId = R.id.mainFragmentContainer,
                level = level.name,
                type = WorkoutType.FULL_BODY.name
            ),

            fragmentManager = parentFragmentManager
        )
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

    override fun openWorkout(
        level: WorkoutLevel,
        type: WorkoutType
    ) {
        RouterHolder.router.navigateToFragment(
            screen = Screen.WorkoutRunningFragmentRoute(
                fromContext = requireActivity(),
                containerId = R.id.mainFragmentContainer,
                level = level.name,
                type = type.name
            ),
            fragmentManager = parentFragmentManager
        )
    }

    override fun onDestroyView() {

        _binding = null

        super.onDestroyView()
    }

    companion object {
        fun newWorkoutInstance() = WorkoutFragment()
    }
}