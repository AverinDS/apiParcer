package com.dmitry.apiparcer.fragments.details_repository_fragment

import com.dmitry.apiparcer.repositories.Interactor

sealed class DetailsRepositoryDialogViewState {
    object Loading : DetailsRepositoryDialogViewState()
    data class RepositoryInfoLoaded(val repositoryInfo: Interactor.RepositoryData) : DetailsRepositoryDialogViewState()
    object Closing : DetailsRepositoryDialogViewState()
    data class Error(val message: String) : DetailsRepositoryDialogViewState()
}