package com.dmitry.apiparcer.fragments.details_repository_fragment

import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable

interface DetailsRepositoryDialogView : MvpView {
    /**
     * start loading info
     */
    fun loadingIntent(): Observable<Unit>

    /**
     * close this view
     */
    fun closingIntent(): Observable<Unit>

    /**
     * render state to view
     */
    fun render(state: DetailsRepositoryDialogViewState)
}