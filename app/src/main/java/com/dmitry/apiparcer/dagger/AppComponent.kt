package com.dmitry.apiparcer.dagger

import com.dmitry.apiparcer.App
import com.dmitry.apiparcer.container_activity.ContainerActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, PresentersModule::class])
interface AppComponent {
    fun inject(app: App)
    fun inject(containerActivity: ContainerActivity)
}