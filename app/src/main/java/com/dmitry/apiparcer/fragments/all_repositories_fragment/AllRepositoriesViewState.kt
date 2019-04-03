package com.dmitry.apiparcer.fragments.all_repositories_fragment

import com.dmitry.apiparcer.json.RepositoryJson

sealed class AllRepositoriesViewState {
    data class LoadRepositories(val repositoryModels: List<RepositoryJson>) : AllRepositoriesViewState()
    data class Error(val message: String) : AllRepositoriesViewState()
}