package com.dmitry.apiparcer.dagger

import com.dmitry.apiparcer.container_activity.ContainerPresenter
import com.dmitry.apiparcer.fragments.all_repositories_fragment.AllRepositoriesPresenter
import com.dmitry.apiparcer.repositories.NetworkRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresentersModule {

    @Provides
    @Singleton
    fun provideContainerPresenter(): ContainerPresenter = ContainerPresenter()

    @Provides
    @Singleton
    fun provideAllRepositoriesPresenter(networkRepository: NetworkRepositoryImpl): AllRepositoriesPresenter =
        AllRepositoriesPresenter(networkRepository)

}