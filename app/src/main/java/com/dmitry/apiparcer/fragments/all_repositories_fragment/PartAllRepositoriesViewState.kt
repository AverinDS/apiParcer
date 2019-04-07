package com.dmitry.apiparcer.fragments.all_repositories_fragment

import com.dmitry.apiparcer.repositories.Interactor


sealed class PartAllRepositoriesViewState {
    data class LoadRepositories(val repositoryModels: List<Interactor.RepositoryData>, val isRefreshed: Boolean) :
        PartAllRepositoriesViewState()
    data class Error(val message: String) : PartAllRepositoriesViewState()
    data class GoToDetails(val repositoryData: Interactor.RepositoryData) : PartAllRepositoriesViewState()
    object Loading : PartAllRepositoriesViewState()
}

data class GeneralAllRepositoriesViewState(
    var isLoading: Boolean,
    var error: String,
    val isRefreshed: Boolean,
    var containsNewData: Boolean,
    val repositoryModels: List<Interactor.RepositoryData>
)