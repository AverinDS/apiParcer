package com.dmitry.apiparcer.fragments.all_repositories_fragment

import com.dmitry.apiparcer.repositories.NetworkRepository
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

class AllRepositoriesPresenter(
    private val networkRepository: NetworkRepository

) : MviBasePresenter<AllRepositoriesViewFragment, AllRepositoriesViewState>() {

    override fun bindIntents() {
        val loadingIntent = intent(AllRepositoriesViewFragment::loadingIntent)
            .flatMap { networkRepository.requestRepositoriesSinceId(0) }
            .observeOn(AndroidSchedulers.mainThread())
            .map { listRepositories -> AllRepositoriesViewState.LoadRepositories(listRepositories) as AllRepositoriesViewState }

        val goToUpIntent = intent(AllRepositoriesViewFragment::goToUp)
            .map { AllRepositoriesViewState.Error("not implemented") as AllRepositoriesViewState }

        val loadNext = intent(AllRepositoriesViewFragment::loadNext)
            .map { AllRepositoriesViewState.Error("not implemented") as AllRepositoriesViewState }

        val allIntents = Observable.merge(listOf(loadingIntent, goToUpIntent, loadNext))

        subscribeViewState(allIntents, AllRepositoriesViewFragment::render)
    }

}