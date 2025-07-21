package com.example.trainingapp.presentation.base

import android.widget.Toast
import moxy.MvpAppCompatActivity

open class BaseActivity : MvpAppCompatActivity(), BaseView  {

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}