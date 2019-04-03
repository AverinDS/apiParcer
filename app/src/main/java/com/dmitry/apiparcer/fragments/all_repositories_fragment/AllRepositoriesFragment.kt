package com.dmitry.apiparcer.fragments.all_repositories_fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dmitry.apiparcer.R
import com.dmitry.apiparcer.adapters.AllRepositoryAdapter
import com.dmitry.apiparcer.app
import com.hannesdorfmann.mosby3.mvi.MviFragment
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_all_repositories.*
import javax.inject.Inject

class AllRepositoriesFragment : MviFragment<AllRepositoriesViewFragment, AllRepositoriesPresenter>(),
    AllRepositoriesViewFragment {

    private val loadNextRepositories: PublishSubject<Int> = PublishSubject.create()
    private val goToUpRepositories: PublishSubject<Unit> = PublishSubject.create()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_all_repositories, container, false)
    }

    override fun loadingIntent(): Observable<Unit> {
        return Observable.just(Unit)
    }

    override fun loadNext(): Observable<Int> = loadNextRepositories

    override fun goToUp(): Observable<Unit> = goToUpRepositories

    override fun render(state: AllRepositoriesViewState) {
        if (state is AllRepositoriesViewState.LoadRepositories) {
            recycleView.layoutManager = LinearLayoutManager(this.context)
            recycleView.adapter = AllRepositoryAdapter(state.repositoryModels)
        }
    }

    @Inject
    lateinit var presenter: AllRepositoriesPresenter

    override fun createPresenter(): AllRepositoriesPresenter {
        activity?.app?.component?.inject(this)
        return presenter
    }

}