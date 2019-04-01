package com.dmitry.apiparcer.dagger

import android.content.Context
import com.dmitry.apiparcer.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: App) {

    @Provides
    @Singleton
    fun provideAppContext(): Context = application.applicationContext
}