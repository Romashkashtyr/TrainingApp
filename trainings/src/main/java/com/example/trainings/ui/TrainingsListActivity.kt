package com.example.trainings.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.base.BaseActivity
import com.example.core.navigation.RouterHolder.router
import com.example.core.navigation.Screen
import androidx.core.widget.addTextChangedListener
import com.example.trainings.data.response.FullExercise
import com.example.trainings.databinding.ActivityTrainingsListBinding
import com.example.trainings.di.TrainingComponent
import com.example.trainings.di.modules.TrainingFactory
import com.example.trainings.ui.rc_view_training.TrainingAdapter
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class TrainingsListActivity : BaseActivity(), TrainingsView {
    private lateinit var binding: ActivityTrainingsListBinding
    private lateinit var trainingAdapter: TrainingAdapter


    @Inject
    lateinit var trainingFactory: TrainingFactory

    private val presenter by moxyPresenter { trainingFactory.createTrainingPresenter() }


    init {
        TrainingComponent.getTrainingInstance().inject(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrainingsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        trainingAdapter = TrainingAdapter()
        initRecyclerView()
        initSearch()
        presenter.loadExercises()

        binding.arrowBack.setOnClickListener {
            router.navigateTo(Screen.Main(this))
            finish()
        }


    }

    override fun showLoading() {
       // binding.trainingPgBar.visibility = View.VISIBLE
    }

    override fun stopLoading() {
      // binding.trainingPgBar.visibility = View.GONE
    }



    override fun showExercises(exercises: List<FullExercise>) {
        Log.d("TrainingsDebug", "showExercises вызван. Получено элементов: ${exercises.size}")

        if (exercises.isEmpty()) {
            Log.d("TrainingsDebug", "ВНИМАНИЕ: Список упражнений пустой!")
        } else {
            Log.d("TrainingsDebug", "Первый элемент: ${exercises.firstOrNull()?.name?.firstOrNull()}")
        }

        trainingAdapter.updateList(exercises)

    }

    private fun initRecyclerView() {
        binding.rcViewTraining.apply {
            layoutManager = LinearLayoutManager(this@TrainingsListActivity)
            adapter = trainingAdapter
        }
    }

    private fun initSearch() {
        binding.searchButton.setOnClickListener {
            val query = binding.searchExercise.text.toString()

            trainingAdapter.filterList(query)
        }

        binding.searchExercise.addTextChangedListener {
            trainingAdapter.filterList(it.toString())
            binding.rcViewTraining.scrollToPosition(0)
        }
    }


    companion object {
        fun getIntent(fromContext: Context): Intent =
            Intent(fromContext, TrainingsListActivity::class.java)
    }

}