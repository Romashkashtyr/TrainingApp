package com.example.splash.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.core.navigation.RouterHolder.router
import com.example.core.navigation.Screen
import com.example.splash.databinding.ActivitySplashBinding
import com.example.splash.di.SplashComponent
import com.example.splash.di.modules.SplashPresenterFactory
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class SplashActivity: MvpAppCompatActivity(), SplashView {

    private lateinit var binding: ActivitySplashBinding

    @Inject
    lateinit var presenterFactory: SplashPresenterFactory

    private val presenter by moxyPresenter { presenterFactory.createSplashPresenter() }

    init {
        SplashComponent.getSplashInstance().inject(this)
    }

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
        router.navigateTo(Screen.Main(this))
        finish()
    }

    override fun navigateToAuthorization() {
        router.navigateTo(Screen.Auth(this))
        finish()
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        router.navigateTo(Screen.Auth(this))
    }
}