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
) : MviBasePresenter<AllRepositoriesView, GeneralAllRepositoriesViewState>() {

    private val disposables = CompositeDisposable()

    override fun bindIntents() {

        val loadingIntent = intent(AllRepositoriesView::loadingIntent)
            .flatMap { interactor.getRepositoriesDataSinceId(startId) }
            .observeOn(AndroidSchedulers.mainThread())
            .map { listRepositories -> PartAllRepositoriesViewState.LoadRepositories(listRepositories) as PartAllRepositoriesViewState }
            .startWith(PartAllRepositoriesViewState.Loading)
            .onErrorReturn { PartAllRepositoriesViewState.Error(it.localizedMessage) }

        val goToUpIntent = intent(AllRepositoriesView::goToUp)
            .map { PartAllRepositoriesViewState.Error("not implemented") as PartAllRepositoriesViewState }

        val loadNext = intent(AllRepositoriesView::loadNext)
            .map { PartAllRepositoriesViewState.Error("not implemented") as PartAllRepositoriesViewState }

        val goToDetails = intent(AllRepositoriesView::goToDetails)
            .map { PartAllRepositoriesViewState.GoToDetails(it) as PartAllRepositoriesViewState }

        val allIntents = Observable.merge(listOf(loadingIntent, goToUpIntent, loadNext, goToDetails)).share()

        disposables.add(
            allIntents
                .filter { it is PartAllRepositoriesViewState.GoToDetails }
                .map { state -> state as PartAllRepositoriesViewState.GoToDetails }
                .subscribe { state -> showDetailsRepository?.invoke(state.repositoryData) }
        )

        val initialState = GeneralAllRepositoriesViewState(
            isLoading = true,
            error = "",
            repositoryModels = emptyList(),
            containsNewData = true
        )
        val stateObservable: Observable<GeneralAllRepositoriesViewState> =
            allIntents.scan(initialState, this::viewStateReducer)
        subscribeViewState(stateObservable, AllRepositoriesView::render)
    }

    private fun viewStateReducer(
        previousState: GeneralAllRepositoriesViewState,
        changes: PartAllRepositoriesViewState
    ): GeneralAllRepositoriesViewState {
        return when (changes) {
            is PartAllRepositoriesViewState.Error -> {
                previousState.copy(
                    error = changes.message,
                    isLoading = false,
                    repositoryModels = emptyList(),
                    containsNewData = false
                )
            }
            is PartAllRepositoriesViewState.LoadRepositories -> {
                previousState.copy(
                    error = "",
                    isLoading = false,
                    repositoryModels = previousState.repositoryModels + changes.repositoryModels,
                    containsNewData = true
                )
            }
            is PartAllRepositoriesViewState.Loading -> {
                previousState.copy(error = "", isLoading = true, repositoryModels = emptyList(), containsNewData = true)
            }
            else -> previousState.copy(containsNewData = false)
        }
    }

    override fun unbindIntents() {
        disposables.clear()
        showDetailsRepository = null
        super.unbindIntents()
    }

}