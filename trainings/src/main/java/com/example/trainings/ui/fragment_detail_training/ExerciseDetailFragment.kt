package com.example.trainings.ui.fragment_detail_training

import android.os.Bundle
import android.view.View
import com.example.core.base.BaseFragment
import com.example.trainings.R
import com.example.trainings.data.response.FullExercise
import com.example.trainings.databinding.DetailExerciseFragmentBinding
import moxy.InjectViewState


@InjectViewState
class ExerciseDetailFragment: BaseFragment(), ExerciseDetailView {


    private var _binding: DetailExerciseFragmentBinding? = null
    private val binding get() = _binding!!

    private var exerciseId: String? = null
    private var isFavorite = false


    override fun showExercise(exercise: FullExercise) {
        TODO("Not yet implemented")
    }

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun stopLoading() {
        TODO("Not yet implemented")
    }

    override fun updateFavoriteState(isFavorite: Boolean) {
        binding.toggleFavorite.text = if (isFavorite) {
            getString(R.string.remove_from_favorite)
        } else {
            getString(R.string.add_to_favorite)
        }
    }


    override fun showToastInfo(message: String) {
        TODO("Not yet implemented")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = DetailExerciseFragmentBinding.bind(view)

        exerciseId = arguments?.getString(ARG_ID)
    }

    private fun setupClicks() {
        binding.arrowBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.toggleFavorite.setOnClickListener {
            isFavorite != isFavorite
            updateFavoriteState(isFavorite)
        }

    }

    private fun loadData() {
        val id = exerciseId ?: return

        val url = "https://api.workoutapi.com/exercises/$id/image"
    }

    companion object {
        private const val ARG_ID = "exercise_id"

        fun newInstance(id: String) =
            ExerciseDetailFragment().apply {
                arguments = Bundle(). apply {
                    putString(ARG_ID,id)
                }
            }
    }
}