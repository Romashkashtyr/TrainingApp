package com.example.trainingapp.presentation.base.base

import android.widget.Toast
import moxy.MvpAppCompatActivity

open class BaseActivity : MvpAppCompatActivity(), BaseView {

    override fun showToast(message: Int) {
        Toast.makeText(this, getString(message), Toast.LENGTH_SHORT).show()
    }
}