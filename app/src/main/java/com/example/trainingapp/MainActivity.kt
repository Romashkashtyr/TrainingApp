package com.example.trainingapp

import android.os.Bundle
import com.example.trainingapp.start.MVPViewInterface
import moxy.MvpAppCompatActivity

class MainActivity : MvpAppCompatActivity(), MVPViewInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}