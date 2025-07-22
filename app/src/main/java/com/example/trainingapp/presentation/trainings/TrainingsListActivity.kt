package com.example.trainingapp.presentation.trainings

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trainingapp.databinding.ActivityTrainingsListBinding
import com.example.trainingapp.domain.Training
import com.example.trainingapp.domain.di.modules.TrainingFactory
import com.example.trainingapp.presentation.TrainingApp
import com.example.trainingapp.presentation.base.BaseActivity
import com.example.trainingapp.presentation.main.MainActivity
import com.example.trainingapp.presentation.trainings.rc_view_training.TrainingAdapter
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class TrainingsListActivity : BaseActivity(), TrainingsView {
    private lateinit var binding: ActivityTrainingsListBinding
    private lateinit var trainingAdapter: TrainingAdapter


    @Inject
    lateinit var trainingFactory: TrainingFactory

    private val presenter by moxyPresenter { trainingFactory.createTrainingPresenter() }


    init {
        TrainingApp.component.inject(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TrainingApp.component.inject(this)
        binding = ActivityTrainingsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter.requestTrainingList()

        binding.arrowBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }


    override fun showTrainingsList(trainingList: List<Training>) {
        trainingAdapter = TrainingAdapter(trainingList)
        binding.rcViewTraining.apply {
            layoutManager = LinearLayoutManager(this@TrainingsListActivity)
            adapter = trainingAdapter
        }
    }

}