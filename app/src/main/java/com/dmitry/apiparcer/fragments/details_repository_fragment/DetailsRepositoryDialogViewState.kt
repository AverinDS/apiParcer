package com.dmitry.apiparcer.fragments.details_repository_fragment


sealed class DetailsRepositoryDialogViewState {
    object Loading : DetailsRepositoryDialogViewState()
    data class RepositoryInfoLoaded(val commits: List<DetailsRepositoryDialogView.AuthorCommit>) :
        DetailsRepositoryDialogViewState()
    object Closing : DetailsRepositoryDialogViewState()
    data class Error(val message: String) : DetailsRepositoryDialogViewState()
}