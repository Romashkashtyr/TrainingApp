package com.example.main.ui.workout_fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.base.BaseFragment
import com.example.main.data.entitieModules.WorkoutExercise
import com.example.main.data.entitieModules.WorkoutHistory
import com.example.main.databinding.WorkoutFragmentHistoryBinding
import com.example.main.di.MainComponent
import com.example.main.di.modules.WorkoutFragmentFactory
import com.example.main.ui.workout_rc_view.WorkoutHistoryAdapter
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class WorkoutFragment: BaseFragment(), WorkoutView {

    private var _binding: WorkoutFragmentHistoryBinding? = null
    private val binding
        get() = _binding!!

    @Inject
    lateinit var factory: WorkoutFragmentFactory

    private val presenter by moxyPresenter {
        factory.createPresenter()
    }

    private lateinit var adapter: WorkoutHistoryAdapter

    override fun onAttach(context: Context) {
        MainComponent.getMainInstance().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = WorkoutFragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()

        binding.arrowBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun initRecycler() {
        adapter = WorkoutHistoryAdapter()

        binding.historyRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@WorkoutFragment.adapter
            setHasFixedSize(true)
        }
    }

    override fun showWorkoutHistory(history: List<WorkoutHistory>) {
        adapter.submitList(history)

        binding.workoutCount.text = "Всего тренировок: ${history.size}"

        if (history.isEmpty()) {
            showEmptyHistory()
        } else {
            hideEmptyHistory()
        }
    }

    override fun showWorkout(workout: List<WorkoutExercise>) {
        TODO("Not yet implemented")
    }

    override fun showExercise(
        exercise: WorkoutExercise,
        position: Int,
        total: Int
    ) {
        TODO("Not yet implemented")
    }

    override fun updateTimer(seconds: Int) {
        TODO("Not yet implemented")
    }

    override fun showWorkoutFinished() {
        TODO("Not yet implemented")
    }

    override fun showError(message: String) {
        TODO("Not yet implemented")
    }


    override fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }

    override fun showEmptyHistory() {
        binding.emptyHistoryText.visibility = View.VISIBLE
        binding.historyRecyclerView.visibility = View.GONE
    }

    override fun hideEmptyHistory() {
        binding.emptyHistoryText.visibility = View.GONE
        binding.historyRecyclerView.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newWorkoutInstance(): WorkoutFragment {
            return WorkoutFragment()
        }
    }
}