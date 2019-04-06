package com.dmitry.apiparcer.fragments.all_repositories_fragment

import com.dmitry.apiparcer.repositories.Interactor


sealed class AllRepositoriesViewState {
    data class LoadRepositories(val repositoryModels: List<Interactor.RepositoryData>) : AllRepositoriesViewState()
    data class Error(val message: String) : AllRepositoriesViewState()
    data class GoToDetails(val repositoryData: Interactor.RepositoryData) : AllRepositoriesViewState()

    object Loading : AllRepositoriesViewState()
}