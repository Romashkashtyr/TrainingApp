package com.example.core.base

import android.util.Log
import com.example.core.R
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import moxy.MvpPresenter
import kotlin.coroutines.CoroutineContext

@InjectViewState
open class BasePresenter<T : BaseView> : MvpPresenter<T>(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + SupervisorJob() + exceptionHandler

    override fun destroyView(view: T?) {
        super.destroyView(view)
        coroutineContext.cancelChildren()
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("ExceptionHandler", throwable.stackTraceToString())
        requireShowToast(R.string.error_error)
    }

    private fun requireShowToast(message: Int) {
        launch(Dispatchers.Main) {
            viewState.showToast(message)
        }
    }


    suspend fun onMainThread(action: suspend () -> Unit) {
        withContext(Dispatchers.Main) {
            action()
        }
    }



}