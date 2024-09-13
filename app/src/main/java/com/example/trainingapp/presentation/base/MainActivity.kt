package com.example.trainingapp.presentation.base

import android.os.Bundle
import com.example.trainingapp.R
import moxy.MvpAppCompatActivity

class MainActivity : MvpAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}