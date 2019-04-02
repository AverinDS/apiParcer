package com.dmitry.apiparcer.dagger

import com.dmitry.apiparcer.GitHubApi
import com.dmitry.apiparcer.repositories.NetworkRepository
import com.dmitry.apiparcer.repositories.NetworkRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoriesModule {

    @Provides
    @Singleton
    fun provideNetworkRepository(api: GitHubApi): NetworkRepository = NetworkRepositoryImpl(api)
}