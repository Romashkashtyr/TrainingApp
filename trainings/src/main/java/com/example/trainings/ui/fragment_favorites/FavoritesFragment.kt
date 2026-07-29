package com.example.trainings.ui.fragment_favorites

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.base.BaseFragment
import com.example.trainings.R
import com.example.trainings.data.response.FullExercise
import com.example.trainings.databinding.FragmentFavoritesBinding
import com.example.trainings.di.TrainingComponent
import com.example.trainings.di.modules.FavoritesFactory
import com.example.trainings.ui.fragment_detail_training.ExerciseDetailFragment
import com.example.trainings.ui.rc_view_training.TrainingAdapter
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class FavoritesFragment : BaseFragment(), FavoritesView, ExerciseDetailFragment.DetailsListener {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: FavoritesFactory

    private val presenter by moxyPresenter {
        factory.createFavoritesPresenter()
    }

    private lateinit var adapter: TrainingAdapter

    override fun onAttach(context: Context) {
        TrainingComponent.getTrainingInstance().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        initSearch()

        binding.arrowBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initSearch() {
        binding.searchExercise.addTextChangedListener {
            presenter.search(it.toString())
        }
    }

    private fun initRecycler() {
        adapter = TrainingAdapter (
            onDetailClick = { exercise ->
                parentFragmentManager.beginTransaction()
                    .replace(
                        R.id.fragment_container,
                        ExerciseDetailFragment.newInstance(exercise.id, this)
                    )
                    .addToBackStack(null)
                    .commit()
            },
            onFavoriteClick = { exercise ->
                presenter.toggleFavorite(exercise)
            }
        )

        binding.favoritesRecycler.layoutManager = LinearLayoutManager(requireContext())

        binding.favoritesRecycler.adapter = adapter
    }

    override fun showExercises(exercises: List<FullExercise>) {
        adapter.updateList(exercises)
        binding.favoritesRecycler.scrollToPosition(0)
    }

    override fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun stopLoading() {
        binding.progressBar.visibility = View.GONE
    }

    override fun onFavoriteChange(id: String, isFavorite: Boolean) {
        TODO("Not yet implemented")
    }
}