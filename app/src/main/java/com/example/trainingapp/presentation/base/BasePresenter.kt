package com.example.trainingapp.presentation.base

import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.MvpView

@InjectViewState
open class BasePresenter<T: BaseView> : MvpPresenter<T>() {

    fun requireShowToast(message: String) {
        viewState.showToast(message)
    }




}