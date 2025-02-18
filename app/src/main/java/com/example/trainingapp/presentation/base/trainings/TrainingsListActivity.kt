package com.example.trainingapp.presentation.base.trainings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.trainingapp.R
import com.example.trainingapp.data.TrainingsRepositoryImpl
import com.example.trainingapp.databinding.ActivityTrainingsListBinding
import com.example.trainingapp.domain.DashboardItem
import com.example.trainingapp.domain.Training
import com.example.trainingapp.domain.di.ApplicationComponent
import com.example.trainingapp.domain.di.DaggerApplicationComponent
import com.example.trainingapp.domain.di.TrainingApp
import com.example.trainingapp.presentation.base.BaseActivity
import com.example.trainingapp.presentation.base.main.rc_view.DashboardViewHolder
import com.example.trainingapp.presentation.base.trainings.rc_view_training.TrainingAdapter
import javax.inject.Inject

class TrainingsListActivity : BaseActivity(), TrainingsView {
    private lateinit var binding: ActivityTrainingsListBinding
    private lateinit var trainingAdapter: TrainingAdapter


    private val component by lazy {
        (application as TrainingApp).component
    }

    @Inject
    lateinit var repositoryImpl: TrainingsRepositoryImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityTrainingsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun showTrainingsList() {
        val trainingWorkout = repositoryImpl.getTrainingList()
        trainingAdapter = TrainingAdapter(trainingWorkout)
        binding.rcViewTraining.apply {
            layoutManager = LinearLayoutManager(this@TrainingsListActivity)
            adapter = trainingAdapter
        }
    }

}