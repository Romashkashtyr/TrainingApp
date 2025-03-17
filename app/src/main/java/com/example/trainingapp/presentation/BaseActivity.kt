package com.example.trainingapp.presentation

import android.widget.Toast
import com.example.trainingapp.presentation.base.BaseView
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

open class BaseActivity : MvpAppCompatActivity(), BaseView {

    override fun showToast(message: Int) {
        Toast.makeText(this, getString(message), Toast.LENGTH_SHORT).show()
    }
}