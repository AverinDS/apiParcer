package com.dmitry.apiparcer.fragments.details_repository_fragment

import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable

interface DetailsRepositoryDialogView : MvpView {
    /**
     * start loading info
     */
    fun loadingIntent(): Observable<Unit>

    /**
     * when arguments was picked
     */
    fun argumentsPicked(): Observable<List<AuthorCommit>>

    /**
     * close this view
     */
    fun closingIntent(): Observable<Unit>

    /**
     * render state to view
     */
    fun render(state: DetailsRepositoryDialogViewState)

    data class AuthorCommit(
        val author: String,
        val commit: String
    )

}