package com.example.trainingapp.presentation.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.MvpView
import kotlin.coroutines.CoroutineContext

@InjectViewState
open class BasePresenter<T: BaseView>() : MvpPresenter<T>(), CoroutineScope {

    fun requireShowToast(message: Int) {
        viewState.showToast(message)
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + SupervisorJob()


}