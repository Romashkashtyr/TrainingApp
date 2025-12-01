package com.example.core.base

import android.widget.Toast
import moxy.MvpAppCompatActivity

open class BaseActivity : MvpAppCompatActivity(), BaseView {

    override fun showToast(message: Int) {
        Toast.makeText(this, getString(message), Toast.LENGTH_SHORT).show()
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}