package com.example.trainingapp.presentation.trainings

import android.os.Bundle
import com.example.trainingapp.databinding.ActivityTrainingsListBinding
import com.example.trainingapp.domain.Training
import com.example.trainingapp.presentation.base.BaseActivity
import com.example.trainingapp.presentation.trainings.rc_view_training.TrainingAdapter

class TrainingsListActivity : BaseActivity(), TrainingsView {
    private lateinit var binding: ActivityTrainingsListBinding
    private lateinit var trainingAdapter: TrainingAdapter




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrainingsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        }



    override fun showTrainingsList(trainingList: List<Training>) {
        val trainingsList = arrayListOf<Training>()
        trainingAdapter = TrainingAdapter(trainingsList)

    }



}