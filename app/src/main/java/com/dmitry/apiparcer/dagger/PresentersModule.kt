package com.dmitry.apiparcer.dagger

import com.dmitry.apiparcer.container_activity.ContainerPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresentersModule {

    @Provides
    @Singleton
    fun provideContainerPresenter(): ContainerPresenter {
        return ContainerPresenter()
    }

}