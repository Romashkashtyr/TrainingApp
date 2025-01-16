package com.example.trainingapp.presentation.base.trainings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.trainingapp.R
import com.example.trainingapp.databinding.ActivityTrainingsListBinding
import com.example.trainingapp.presentation.base.BaseActivity

class TrainingsListActivity : BaseActivity(), TrainingsView {
    private lateinit var binding: ActivityTrainingsListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrainingsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        }

}