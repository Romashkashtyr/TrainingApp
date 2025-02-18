package com.example.trainingapp.presentation.base

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.MvpView
import kotlin.coroutines.CoroutineContext

@InjectViewState
open class BasePresenter<T: BaseView> : MvpPresenter<T>(), CoroutineScope {

    private val exceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        println(throwable.message)
    }

    fun requireShowToast(message: Int) {
        viewState.showToast(message)
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + SupervisorJob() + exceptionHandler


}