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
            .flatMap { getFirstLoadingState() }

        val goToUpIntent = intent(AllRepositoriesView::goToUp)
            .map { PartAllRepositoriesViewState.Error("not implemented") as PartAllRepositoriesViewState }

        val loadNext = intent(AllRepositoriesView::loadNext)
            .map { PartAllRepositoriesViewState.Error("not implemented") as PartAllRepositoriesViewState }

        val goToDetails = intent(AllRepositoriesView::goToDetails)
            .map { PartAllRepositoriesViewState.GoToDetails(it) as PartAllRepositoriesViewState }

        val refreshData = intent(AllRepositoriesView::refreshDataRepositories)
            .flatMap { getFirstLoadingState() }

        val allIntents =
            Observable.merge(listOf(loadingIntent, goToUpIntent, loadNext, goToDetails, refreshData)).share()

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
            containsNewData = true,
            isRefreshed = false
        )
        val stateObservable: Observable<GeneralAllRepositoriesViewState> =
            allIntents.scan(initialState, this::viewStateReducer)
        subscribeViewState(stateObservable, AllRepositoriesView::render)
    }


    private fun getFirstLoadingState(): Observable<PartAllRepositoriesViewState> {
        return interactor.getRepositoriesDataSinceId(startId)
            .observeOn(AndroidSchedulers.mainThread())
            .map { listRepositories ->
                PartAllRepositoriesViewState.LoadRepositories(
                    listRepositories,
                    true
                ) as PartAllRepositoriesViewState
            }
            .startWith(PartAllRepositoriesViewState.Loading)
            .onErrorReturn { PartAllRepositoriesViewState.Error(it.localizedMessage) }

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
                    containsNewData = false,
                    isRefreshed = false
                )
            }
            is PartAllRepositoriesViewState.LoadRepositories -> {
                previousState.copy(
                    error = "",
                    isLoading = false,
                    repositoryModels = if (changes.isRefreshed) {
                        changes.repositoryModels
                    } else {
                        previousState.repositoryModels + changes.repositoryModels
                    },
                    containsNewData = true,
                    isRefreshed = changes.isRefreshed
                )
            }
            is PartAllRepositoriesViewState.Loading -> {
                previousState.copy(
                    error = "",
                    isLoading = true,
                    repositoryModels = emptyList(),
                    containsNewData = true,
                    isRefreshed = false
                )
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