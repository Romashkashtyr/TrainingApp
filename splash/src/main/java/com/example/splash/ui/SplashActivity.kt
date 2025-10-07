package com.example.splash.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.core.RouterHolder
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class SplashActivity: MvpAppCompatActivity(), SplashView {

    private lateinit var binding: ActivitySplashBinding

    @Inject
    lateinit var presenterFactory: SplashPresenterFactory

    private val presenter by moxyPresenter { presenterFactory.createSplashPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter.checkAuthorization()
    }

    override fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progressBar.visibility = View.GONE
    }

    override fun navigateToMain() {
        finish()
    }

    override fun navigateToAuthorization() {
        RouterHolder.router?.navigateToAuth(this)
        finish()
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        navigateToAuthorization()
    }
}