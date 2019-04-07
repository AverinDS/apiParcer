package com.dmitry.apiparcer.fragments.all_repositories_fragment

import com.dmitry.apiparcer.repositories.Interactor


sealed class PartAllRepositoriesViewState {
    data class LoadRepositories(val repositoryModels: List<Interactor.RepositoryData>, val isRefreshed: Boolean) :
        PartAllRepositoriesViewState()

    data class Error(val messageCode: Int) : PartAllRepositoriesViewState()
    data class GoToDetails(val repositoryData: Interactor.RepositoryData) : PartAllRepositoriesViewState()
    object Loading : PartAllRepositoriesViewState()
    object EmptyState : PartAllRepositoriesViewState()
}

data class GeneralAllRepositoriesViewState(
    var isLoading: Boolean,
    var errorCode: Int,
    val isRefreshed: Boolean,
    val repositoryModels: List<Interactor.RepositoryData>
)

