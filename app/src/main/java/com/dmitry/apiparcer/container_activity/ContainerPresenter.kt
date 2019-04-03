package com.dmitry.apiparcer.container_activity

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter

class ContainerPresenter : MviBasePresenter<ContainerView, ContainerViewState>() {

    override fun bindIntents() {

        val loadingIntent = intent(ContainerView::loadingIntent)
            .map { ContainerViewState.LoadSuccess as ContainerViewState }

        subscribeViewState(loadingIntent, ContainerView::render)
    }

}