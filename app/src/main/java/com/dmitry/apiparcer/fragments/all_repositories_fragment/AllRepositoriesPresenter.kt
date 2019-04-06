package com.dmitry.apiparcer.fragments.all_repositories_fragment

import com.dmitry.apiparcer.repositories.Interactor
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

const val startId = 0

class AllRepositoriesPresenter(
    private val interactor: Interactor,
    private var showDetailsRepository: ((dataRepository: Interactor.RepositoryData) -> Unit)?
) : MviBasePresenter<AllRepositoriesView, AllRepositoriesViewState>() {

    private val disposables = CompositeDisposable()

    override fun bindIntents() {

        val loadingIntent = intent(AllRepositoriesView::loadingIntent)
            .flatMap { interactor.getRepositoriesDataSinceId(startId) }
            .observeOn(AndroidSchedulers.mainThread())
            .map { listRepositories -> AllRepositoriesViewState.LoadRepositories(listRepositories) as AllRepositoriesViewState }
            .startWith(AllRepositoriesViewState.Loading)
            .onErrorReturn { AllRepositoriesViewState.Error(it.localizedMessage) }

        val goToUpIntent = intent(AllRepositoriesView::goToUp)
            .map { AllRepositoriesViewState.Error("not implemented") as AllRepositoriesViewState }

        val loadNext = intent(AllRepositoriesView::loadNext)
            .map { AllRepositoriesViewState.Error("not implemented") as AllRepositoriesViewState }

        val goToDetails = intent(AllRepositoriesView::goToDetails)
            .map { AllRepositoriesViewState.GoToDetails(it) }

        val allIntents = Observable.merge(listOf(loadingIntent, goToUpIntent, loadNext, goToDetails)).share()

        disposables.add(
            allIntents
                .filter { it is AllRepositoriesViewState.GoToDetails }
                .map { state -> state as AllRepositoriesViewState.GoToDetails }
                .subscribe { state -> showDetailsRepository?.invoke(state.repositoryData) }
        )

        subscribeViewState(allIntents, AllRepositoriesView::render)
    }

    override fun unbindIntents() {
        disposables.clear()
        showDetailsRepository = null
        super.unbindIntents()
    }

}