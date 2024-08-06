package com.example.trainingapp.presentation.base

import android.widget.Toast
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class BaseActivity : MvpAppCompatActivity(), BaseView  {

    val presenter by moxyPresenter { BasePresenter<BaseView>() }
    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}