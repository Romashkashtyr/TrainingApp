package com.example.trainingapp.presentation.base.trainings

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trainingapp.databinding.ActivityTrainingsListBinding
import com.example.trainingapp.domain.di.TrainingApp
import com.example.trainingapp.presentation.base.BaseActivity
import com.example.trainingapp.presentation.base.trainings.rc_view_training.TrainingAdapter
import kotlinx.coroutines.launch
import moxy.ktx.moxyPresenter

class TrainingsListActivity : BaseActivity(), TrainingsView {
    private lateinit var binding: ActivityTrainingsListBinding
    private lateinit var trainingAdapter: TrainingAdapter
    private val presenter by moxyPresenter { TrainingsPresenter() }



    private val component by lazy {
        (application as TrainingApp).component
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityTrainingsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    override fun showTrainingsList() {
        lifecycleScope.launch {
            val trainingWorkout = presenter.getTrainingList()
            trainingAdapter = TrainingAdapter(trainingWorkout)
        }
        binding.rcViewTraining.apply {
            layoutManager = LinearLayoutManager(this@TrainingsListActivity)
            adapter = trainingAdapter
        }
        TODO()
    }

}