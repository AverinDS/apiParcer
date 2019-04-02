package com.dmitry.apiparcer.fragments.all_repositories_fragment

import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable

interface AllRepositoriesViewFragment : MvpView {
    fun loadingIntent(): Observable<Unit>
    fun loadNext(): Observable<Int>
    fun goToUp(): Observable<Unit>

    fun render(state: AllRepositoriesViewState)
}