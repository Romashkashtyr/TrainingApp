package com.example.core.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component

@Component(modules = [CoreModule::class])
interface CoreComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): CoreComponent

    }

}