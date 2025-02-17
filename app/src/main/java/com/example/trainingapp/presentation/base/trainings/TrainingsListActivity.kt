package com.example.trainingapp.presentation.base.trainings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.trainingapp.R
import com.example.trainingapp.databinding.ActivityTrainingsListBinding
import com.example.trainingapp.domain.DashboardItem
import com.example.trainingapp.domain.Training
import com.example.trainingapp.domain.di.ApplicationComponent
import com.example.trainingapp.domain.di.DaggerApplicationComponent
import com.example.trainingapp.domain.di.TrainingApp
import com.example.trainingapp.presentation.base.BaseActivity
import com.example.trainingapp.presentation.base.main.rc_view.DashboardViewHolder
import com.example.trainingapp.presentation.base.trainings.rc_view_training.TrainingAdapter

class TrainingsListActivity : BaseActivity(), TrainingsView {
    private lateinit var binding: ActivityTrainingsListBinding
    private lateinit var trainingAdapter: TrainingAdapter


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
        val trainingsList = arrayListOf<Training>()
        trainingAdapter = TrainingAdapter(trainingsList)
    }

}