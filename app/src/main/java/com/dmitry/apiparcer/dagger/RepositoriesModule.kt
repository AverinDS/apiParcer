package com.dmitry.apiparcer.dagger

import com.dmitry.apiparcer.GitHubApi
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

    @Module
    interface BindsModule {
        @Binds
        fun provideNetwork(networkRepository: NetworkRepositoryImpl): NetworkRepository
    }
}