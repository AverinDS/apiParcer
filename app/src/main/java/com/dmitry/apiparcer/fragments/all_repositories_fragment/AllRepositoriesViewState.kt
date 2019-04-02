package com.dmitry.apiparcer.fragments.all_repositories_fragment

import com.dmitry.apiparcer.models.RepositoryModel

sealed class AllRepositoriesViewState {
    data class LoadRepositories(val repositoryModels: List<RepositoryModel>) : AllRepositoriesViewState()
    data class Error(val message: String) : AllRepositoriesViewState()
}