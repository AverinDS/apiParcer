package com.dmitry.apiparcer.container_activity

import android.os.Bundle
import com.dmitry.apiparcer.App
import com.dmitry.apiparcer.R
import com.dmitry.apiparcer.app
import com.hannesdorfmann.mosby3.mvi.MviActivity
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_container.*

class ContainerActivity : MviActivity<ContainerView, ContainerPresenter>(), ContainerView {

    override fun loadingIntent(): Observable<Unit> {
        return Observable.just(Unit)
    }

    override fun render(state: ContainerViewState) {
        if (state is ContainerViewState.Hello) {
            example_text_view.text = state.exampleText
        }
    }

    override fun createPresenter(): ContainerPresenter {
        val navigator = (application as App).component.getNavigator()
        navigator.initialize(supportFragmentManager)
        return app.component.getContainerPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
    }
}

