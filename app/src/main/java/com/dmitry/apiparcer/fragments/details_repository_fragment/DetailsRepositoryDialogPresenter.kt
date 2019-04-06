package com.dmitry.apiparcer.fragments.details_repository_fragment

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

class DetailsRepositoryDialogPresenter(
    var goBack: (() -> Unit)?
) :
    MviBasePresenter<DetailsRepositoryDialogView, DetailsRepositoryDialogViewState>() {

    private val disposables = CompositeDisposable()

    override fun bindIntents() {
        val loadingIntent = intent(DetailsRepositoryDialogView::loadingIntent)
            .map { DetailsRepositoryDialogViewState.Loading }

        val closingIntent = intent(DetailsRepositoryDialogView::closingIntent)
            .map { DetailsRepositoryDialogViewState.Closing }

        val argumentsPickedIntent = intent(DetailsRepositoryDialogView::argumentsPicked)
            .map { DetailsRepositoryDialogViewState.RepositoryInfoLoaded(it) }

        val allIntents = Observable.merge(loadingIntent, closingIntent, argumentsPickedIntent).share()

        disposables.add(
            allIntents
                .filter { state ->
                    state is DetailsRepositoryDialogViewState.Closing
                }
                .subscribe { goBack?.invoke() }
        )

        subscribeViewState(allIntents, DetailsRepositoryDialogView::render)
    }
}