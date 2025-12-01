package com.example.core.base

import android.util.Log
import com.example.trainingapp.R
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.MvpView
import kotlin.coroutines.CoroutineContext

@InjectViewState
open class BasePresenter<T: BaseView> : MvpPresenter<T>(), CoroutineScope {

    private val exceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        Log.e("ExceptionHandler", throwable.stackTraceToString())
        requireShowToast(R.string.sign_up_failure)
    }

    private fun requireShowToast(message: Int) {
        launch {
            withContext(Dispatchers.Main) {
                viewState.showToast(message)
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + SupervisorJob() + exceptionHandler


}