package com.example.trainingapp.presentation.splash

import androidx.appcompat.app.AppCompatActivity
import com.example.trainingapp.databinding.ActivitySplashBinding
import com.example.trainingapp.domain.di.modules.PresenterFactory
import com.example.trainingapp.domain.di.modules.SplashPresenterFactory
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class SplashActivity: MvpAppCompatActivity(), SplashView{

    private lateinit var binding: ActivitySplashBinding

    @Inject
    lateinit var presenterFactory: SplashPresenterFactory

    private val presenter by moxyPresenter { presenterFactory.createSplashPresenter() }


    override fun showProgress() {
        TODO("Not yet implemented")
    }

    override fun hideProgress() {
        TODO("Not yet implemented")
    }

    override fun navigateToMain() {
        TODO("Not yet implemented")
    }

    override fun navigateToAuthorization() {
        TODO("Not yet implemented")
    }

    override fun showError(message: String) {
        TODO("Not yet implemented")
    }
}