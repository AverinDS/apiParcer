package com.dmitry.apiparcer.container_activity

import android.os.Bundle
import com.dmitry.apiparcer.App
import com.dmitry.apiparcer.R
import com.hannesdorfmann.mosby3.mvi.MviActivity
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_container.*
import javax.inject.Inject

class ContainerActivity : MviActivity<ContainerView, ContainerPresenter>(), ContainerView {

    @Inject
    lateinit var presenter: ContainerPresenter

    override fun loadingIntent(): Observable<Unit> {
        return Observable.just(Unit)
    }

    override fun render(state: ContainerViewState) {
        if (state is ContainerViewState.Hello) {
            example_text_view.text = state.exampleText
        }
    }

    override fun createPresenter(): ContainerPresenter {
        (application as App).component.inject(this)
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
    }
}

