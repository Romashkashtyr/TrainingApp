package com.example.trainingapp.presentation.base

import android.widget.Toast
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

open class BaseActivity : MvpAppCompatActivity(), BaseView  {

    override fun showToast(message: Int) {
        Toast.makeText(this, getString(message), Toast.LENGTH_SHORT).show()
    }
}