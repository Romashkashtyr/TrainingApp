package com.example.trainingapp.presentation.splash

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.trainingapp.databinding.ActivitySplashBinding
import com.example.trainingapp.domain.di.modules.SplashPresenterFactory
import com.example.trainingapp.presentation.TrainingApp
import com.example.trainingapp.presentation.main.MainActivity
import com.example.trainingapp.presentation.authorization.AuthorizationActivity
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class SplashActivity: MvpAppCompatActivity(), SplashView{

    private lateinit var binding: ActivitySplashBinding

    @Inject
    lateinit var presenterFactory: SplashPresenterFactory

    private val presenter by moxyPresenter { presenterFactory.createSplashPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        TrainingApp.component.inject(this)
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
        startActivity(MainActivity.getInstance(this))
        finish()
    }

    override fun navigateToAuthorization() {
        startActivity(AuthorizationActivity.getInstance(this))
        finish()
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        navigateToAuthorization()
    }
}