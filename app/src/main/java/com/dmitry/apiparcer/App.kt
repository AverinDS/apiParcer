package com.dmitry.apiparcer

import android.app.Application
import com.dmitry.apiparcer.dagger.AppModule
import com.dmitry.apiparcer.dagger.DaggerAppComponent

class App : Application() {

    val component = DaggerAppComponent.builder().appModule(AppModule(this)).build()

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}