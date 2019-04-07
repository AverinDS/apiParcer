package com.dmitry.apiparcer.fragments.details_repository_fragment

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import io.reactivex.Observable

class DetailsRepositoryDialogPresenter :
    MviBasePresenter<DetailsRepositoryDialogView, DetailsRepositoryDialogViewState>() {

    override fun bindIntents() {
        val loadingIntent = intent(DetailsRepositoryDialogView::loadingIntent)
            .map { DetailsRepositoryDialogViewState.Loading }

        val closingIntent = intent(DetailsRepositoryDialogView::closingIntent)
            .map { DetailsRepositoryDialogViewState.Closing }

        val argumentsPickedIntent = intent(DetailsRepositoryDialogView::argumentsPicked)
            .map { DetailsRepositoryDialogViewState.RepositoryInfoLoaded(it) }

        val allIntents = Observable.merge(loadingIntent, closingIntent, argumentsPickedIntent).share()

        subscribeViewState(allIntents, DetailsRepositoryDialogView::render)
    }
}