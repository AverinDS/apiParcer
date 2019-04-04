package com.dmitry.apiparcer.dagger

import com.dmitry.apiparcer.GitHubApi
import com.dmitry.apiparcer.repositories.Interactor
import com.dmitry.apiparcer.repositories.InteractorImpl
import com.dmitry.apiparcer.repositories.NetworkRepository
import com.dmitry.apiparcer.repositories.NetworkRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [RepositoriesModule.BindsModule::class])
class RepositoriesModule {

    @Provides
    @Singleton
    fun provideNetworkRepository(api: GitHubApi): NetworkRepositoryImpl = NetworkRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideInteractorImpl(networkRepository: NetworkRepository): InteractorImpl = InteractorImpl(networkRepository)

    @Module
    interface BindsModule {
        @Binds
        fun provideNetwork(networkRepository: NetworkRepositoryImpl): NetworkRepository

        @Binds
        fun provideInteractor(interactor: InteractorImpl): Interactor
    }
}