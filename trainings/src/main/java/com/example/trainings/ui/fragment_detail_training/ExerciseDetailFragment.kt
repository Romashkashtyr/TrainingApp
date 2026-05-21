package com.example.trainings.ui.fragment_detail_training

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.core.BuildConfig
import com.example.core.base.BaseFragment
import com.example.core.extensions.loadExerciseImage
import com.example.trainings.R
import com.example.trainings.data.response.FullExercise
import com.example.trainings.databinding.DetailExerciseFragmentBinding
import com.example.trainings.di.TrainingComponent
import com.example.trainings.di.modules.TrainingDetailFactory
import com.example.trainings.ui.training_activity.TrainingsListActivity
import moxy.InjectViewState
import moxy.ktx.moxyPresenter
import javax.inject.Inject



class ExerciseDetailFragment: BaseFragment(), ExerciseDetailView {

    private var _binding: DetailExerciseFragmentBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: TrainingDetailFactory

    private var exerciseId: String? = null


    private val presenter by moxyPresenter { factory.createTrainingDetailPresenter() }

    init {
        TrainingComponent
            .getTrainingInstance()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailExerciseFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = DetailExerciseFragmentBinding.bind(view)

        exerciseId = arguments?.getString(ARG_ID)

        setupClicks()

        exerciseId?.let {
             presenter.loadExercise(it)
        }
    }


    override fun showExercise(exercise: FullExercise) {

        with(binding) {
            exerciseName.text = exercise.name

            exerciseDescription.text = exercise.description

            musclesGroup.text = exercise.primaryMuscles.joinToString { it.name }

            exerciseImage.loadExerciseImage(
                buildGlideUrl(exercise.imageUrl),
                binding.imageProgress
            )
        }

//        Glide.with(this)
//            .load(buildGlideUrl(exercise.imageUrl))
//            .into(binding.exerciseImage)
    }

    override fun showLoading() {
        binding.imageProgress.visibility = View.VISIBLE
    }

    override fun stopLoading() {
        binding.imageProgress.visibility = View.GONE
    }

    override fun updateFavoriteState(isFavorite: Boolean) {
        binding.toggleFavorite.text = if (isFavorite) {
            getString(R.string.remove_from_favorite)
        } else {
            getString(R.string.add_to_favorite)
        }
    }


    override fun showToastInfo(message: String) {
        showToastInfo(R.string.error)
    }


    private fun setupClicks() {
        binding.arrowBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.toggleFavorite.setOnClickListener {
            presenter.onFavoriteClicked()
        }

    }

    private fun buildGlideUrl(startUrl: String): GlideUrl =
        GlideUrl(
            startUrl,
            LazyHeaders.Builder()
                .addHeader("Accept", "application/json")
                .addHeader("x-api-key", BuildConfig.TRAINING_API_KEY)
                .build()
        )

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_ID = "exercise_id"

        fun newInstance(id: String) =
            ExerciseDetailFragment().apply {
                arguments = Bundle(). apply {
                    putString(ARG_ID,id)
                }
            }

       // val url = "https://api.workoutapi.com/exercises/$id/image"

    }

}