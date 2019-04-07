package com.dmitry.apiparcer.fragments.all_repositories_fragment

import com.dmitry.apiparcer.repositories.Interactor
import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable

interface AllRepositoriesView : MvpView {

    /**
     * start intent
     */
    fun loadingIntent(): Observable<Unit>

    /**
     * load next pack of repositories
     */
    fun loadNext(): Observable<Int>

    /**
     * open details screen for repository
     */
    fun goToDetails(): Observable<Interactor.RepositoryData>

    /**
     * refresh data repository
     */
    fun refreshDataRepositories(): Observable<Unit>

    /**
     * render state
     */
    fun render(state: GeneralAllRepositoriesViewState)
}