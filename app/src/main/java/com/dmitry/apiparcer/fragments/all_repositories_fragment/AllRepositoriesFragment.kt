package com.dmitry.apiparcer.fragments.all_repositories_fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dmitry.apiparcer.R
import com.dmitry.apiparcer.adapters.AllRepositoryAdapter
import com.dmitry.apiparcer.app
import com.dmitry.apiparcer.repositories.Interactor
import com.hannesdorfmann.mosby3.mvi.MviFragment
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_all_repositories.*
import javax.inject.Inject

class AllRepositoriesFragment : MviFragment<AllRepositoriesView, AllRepositoriesPresenter>(),
    AllRepositoriesView {
    @Inject
    lateinit var presenter: AllRepositoriesPresenter

    private val loadNextRepositories: PublishSubject<Int> = PublishSubject.create()
    private val goToUpRepositories: PublishSubject<Unit> = PublishSubject.create()
    private val goToDetails: PublishSubject<Interactor.RepositoryData> = PublishSubject.create()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_all_repositories, container, false)
    }

    override fun loadingIntent(): Observable<Unit> = Observable.just(Unit)

    override fun goToDetails(): Observable<Interactor.RepositoryData> = goToDetails

    override fun loadNext(): Observable<Int> = loadNextRepositories

    override fun goToUp(): Observable<Unit> = goToUpRepositories

    override fun render(state: GeneralAllRepositoriesViewState) {
        progressBar.visibility = if (state.isLoading) {
            View.VISIBLE
        } else {
            View.GONE
        }

        recycleView.apply {
            visibility = if (state.isLoading || state.error.isNotEmpty()) {
                View.GONE
            } else {
                View.VISIBLE
            }
            layoutManager = LinearLayoutManager(this.context)

            if (adapter == null) {
                adapter = AllRepositoryAdapter(goToDetails, emptyList())
            } else {

                if (state.containsNewData) {
                    (adapter as AllRepositoryAdapter).let {
                        val lastIndex = it.getLastIndex()
                        val changerange = state.repositoryModels.count() - it.itemCount
                        it.addItems(state.repositoryModels)
                        it.notifyItemRangeInserted(lastIndex, changerange)
                    }
                }
            }
        }
    }

    override fun createPresenter(): AllRepositoriesPresenter {
        activity?.app?.component?.inject(this)
        return presenter
    }
}