package com.dmitry.apiparcer.fragments.all_repositories_fragment

import android.util.Log
import com.dmitry.apiparcer.R
import com.dmitry.apiparcer.repositories.Interactor
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

const val START_ID = 0
const val LOG_TAG = "AllRepoPresenter"

class AllRepositoriesPresenter(
    private val interactor: Interactor,
    private var showDetailsRepository: ((dataRepository: Interactor.RepositoryData) -> Unit)?
) : MviBasePresenter<AllRepositoriesView, GeneralAllRepositoriesViewState>() {

    private val disposables = CompositeDisposable()

    override fun bindIntents() {

        val loadingIntent = intent(AllRepositoriesView::loadingIntent)
            .flatMap { getFirstLoadingState() }

        val loadNext = intent(AllRepositoriesView::loadNext)
            .flatMap { lastId -> interactor.getRepositoriesDataSinceId(lastId) }
            .observeOn(AndroidSchedulers.mainThread())
            .map { loadedListRepositories ->
                PartAllRepositoriesViewState.LoadRepositories(
                    repositoryModels = loadedListRepositories,
                    isRefreshed = false
                ) as PartAllRepositoriesViewState
            }
            .onErrorReturn {
                Log.e(LOG_TAG, it.message ?: "Something went wrong")
                PartAllRepositoriesViewState.Error(R.string.error_message)
            }

        val goToDetails = intent(AllRepositoriesView::goToDetails)
            .map { PartAllRepositoriesViewState.GoToDetails(it) as PartAllRepositoriesViewState }

        val refreshData = intent(AllRepositoriesView::refreshDataRepositories)
            .flatMap { getFirstLoadingState() }

        val allIntents =
            Observable.merge(listOf(loadingIntent, loadNext, goToDetails, refreshData)).share()

        disposables.add(
            allIntents
                .filter { it is PartAllRepositoriesViewState.GoToDetails }
                .map { state -> state as PartAllRepositoriesViewState.GoToDetails }
                .subscribe { state -> showDetailsRepository?.invoke(state.repositoryData) }
        )

        val initialState = GeneralAllRepositoriesViewState(
            isLoading = true,
            errorCode = R.integer.no_errors_code,
            repositoryModels = emptyList(),
            isRefreshed = false
        )
        val stateObservable: Observable<GeneralAllRepositoriesViewState> =
            allIntents.scan(initialState, this::viewStateReducer)
        subscribeViewState(stateObservable, AllRepositoriesView::render)
    }


    private fun getFirstLoadingState(): Observable<PartAllRepositoriesViewState> {
        return interactor.getRepositoriesDataSinceId(START_ID)
            .observeOn(AndroidSchedulers.mainThread())
            .map { listRepositories ->
                PartAllRepositoriesViewState.LoadRepositories(
                    listRepositories,
                    true
                ) as PartAllRepositoriesViewState
            }
            .startWith(PartAllRepositoriesViewState.Loading)
            .onErrorReturn {
                Log.e(LOG_TAG, it.message ?: "Something went wrong")
                PartAllRepositoriesViewState.Error(R.string.error_message)
            }

    }

    private fun viewStateReducer(
        previousState: GeneralAllRepositoriesViewState,
        changes: PartAllRepositoriesViewState
    ): GeneralAllRepositoriesViewState {
        return when (changes) {
            is PartAllRepositoriesViewState.Error -> {
                previousState.copy(
                    errorCode = changes.messageCode,
                    isLoading = false,
                    repositoryModels = emptyList(),
                    isRefreshed = false
                )
            }
            is PartAllRepositoriesViewState.LoadRepositories -> {
                previousState.copy(
                    errorCode = R.integer.no_errors_code,
                    isLoading = false,
                    repositoryModels = changes.repositoryModels,
                    isRefreshed = changes.isRefreshed
                )
            }
            is PartAllRepositoriesViewState.Loading -> {
                previousState.copy(
                    errorCode = R.integer.no_errors_code,
                    isLoading = true,
                    repositoryModels = emptyList(),
                    isRefreshed = false
                )
            }
            else -> previousState.copy(isRefreshed = false)
        }
    }

    override fun unbindIntents() {
        disposables.clear()
        showDetailsRepository = null
        super.unbindIntents()
    }

}