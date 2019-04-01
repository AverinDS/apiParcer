package com.dmitry.apiparcer.container_activity

import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable

interface ContainerView : MvpView {
    fun loadingIntent(): Observable<Unit>
    fun render(state: ContainerViewState)
}