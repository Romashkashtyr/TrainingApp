package com.example.trainingapp.presentation.base.trainings

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trainingapp.databinding.ActivityTrainingsListBinding
import com.example.trainingapp.domain.Training
import com.example.trainingapp.domain.di.TrainingApp
import com.example.trainingapp.presentation.base.BaseActivity
import com.example.trainingapp.presentation.base.trainings.rc_view_training.TrainingAdapter
import kotlinx.coroutines.launch
import moxy.ktx.moxyPresenter

class TrainingsListActivity : BaseActivity(), TrainingsView {
    private lateinit var binding: ActivityTrainingsListBinding
    private lateinit var trainingAdapter: TrainingAdapter
    private val presenter by moxyPresenter { TrainingsPresenter() }


    init {
        TrainingApp.component.inject(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrainingsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter.requestTrainingList()
    }


    override fun showTrainingsList(trainingList: List<Training>) {
            trainingAdapter = TrainingAdapter(trainingList)
            binding.rcViewTraining.apply {
            layoutManager = LinearLayoutManager(this@TrainingsListActivity)
            adapter = trainingAdapter
        }
    }

}