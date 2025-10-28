package com.example.trainings.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.base.BaseActivity
import com.example.core.navigation.RouterProvider.router
import com.example.core.navigation.Screen
import com.example.trainingapp.TrainingApp
import com.example.trainings.Training
import com.example.trainings.databinding.ActivityTrainingsListBinding
import com.example.trainings.di.TrainingFactory
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
        TrainingApp.component.inject(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrainingsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter.requestTrainingList()

        binding.arrowBack.setOnClickListener {
           // startActivity(Intent(this, MainActivity::class.java))
            router?.navigateTo(Screen.Main(this))
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

    companion object {
        fun getIntent(fromContext: Context): Intent = Intent(fromContext, TrainingsListActivity::class.java)
    }

}