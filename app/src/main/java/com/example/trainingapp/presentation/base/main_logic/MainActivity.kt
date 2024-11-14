package com.example.trainingapp.presentation.base.main_logic

import android.os.Bundle
import com.example.trainingapp.R
import com.example.trainingapp.presentation.base.BaseActivity
import moxy.MvpAppCompatActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}