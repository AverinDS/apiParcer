package com.dmitry.apiparcer.fragments.all_repositories_fragment

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import io.reactivex.Observable

class AllRepositoriesPresenter : MviBasePresenter<AllRepositoriesViewFragment, AllRepositoriesViewState>() {
    override fun bindIntents() {
        val loadingIntent = intent(AllRepositoriesViewFragment::loadingIntent)
            .map { AllRepositoriesViewState.Error("not implemented") as AllRepositoriesViewState }

        val goToUpIntent = intent(AllRepositoriesViewFragment::goToUp)
            .map { AllRepositoriesViewState.Error("not implemented") as AllRepositoriesViewState }

        val loadNext = intent(AllRepositoriesViewFragment::loadNext)
            .map { AllRepositoriesViewState.Error("not implemented") as AllRepositoriesViewState }

        val allIntents = Observable.merge(listOf(loadingIntent, goToUpIntent, loadNext))

        subscribeViewState(allIntents, AllRepositoriesViewFragment::render)
    }

}