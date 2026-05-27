package com.example.core.base

import android.util.Log
import com.example.core.R
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import moxy.MvpPresenter
import kotlin.coroutines.CoroutineContext

@InjectViewState
open class BasePresenter<T : BaseView> : MvpPresenter<T>(), CoroutineScope {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("ExceptionHandler", throwable.stackTraceToString())
        requireShowToast(R.string.error_error)
    }


    suspend fun onMainThread(action: suspend () -> Unit) {
        withContext(Dispatchers.Main) {
            action()
        }
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