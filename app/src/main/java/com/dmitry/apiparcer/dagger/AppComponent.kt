package com.dmitry.apiparcer.dagger

import com.dmitry.apiparcer.App
import com.dmitry.apiparcer.Navigator
import com.dmitry.apiparcer.container_activity.ContainerPresenter
import com.dmitry.apiparcer.fragments.all_repositories_fragment.AllRepositoriesFragment
import com.dmitry.apiparcer.fragments.details_repository_fragment.DetailsRepositoryDialogFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, PresentersModule::class, RepositoriesModule::class])
interface AppComponent {
    fun inject(app: App)
    fun inject(allRepositoriesFragment: AllRepositoriesFragment)
    fun inject(detailsRepository: DetailsRepositoryDialogFragment)
    fun getNavigator(): Navigator
    fun getContainerPresenter(): ContainerPresenter
}