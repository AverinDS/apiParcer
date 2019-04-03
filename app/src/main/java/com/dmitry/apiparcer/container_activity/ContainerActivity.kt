package com.dmitry.apiparcer.container_activity

import android.os.Bundle
import com.dmitry.apiparcer.App
import com.dmitry.apiparcer.R
import com.dmitry.apiparcer.app
import com.hannesdorfmann.mosby3.mvi.MviActivity
import io.reactivex.Observable

class ContainerActivity : MviActivity<ContainerView, ContainerPresenter>(), ContainerView {

    override fun loadingIntent(): Observable<Unit> {
        return Observable.just(Unit)
    }

    override fun render(state: ContainerViewState) {

    }

    override fun createPresenter(): ContainerPresenter {
        val navigator = (application as App).component.getNavigator()
        navigator.initialize(supportFragmentManager)
        return app.component.getContainerPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        app.component.getNavigator().let { navigator ->
            navigator.initialize(supportFragmentManager)
            navigator.showAllRepositoriesFragment()
        }
    }
}

