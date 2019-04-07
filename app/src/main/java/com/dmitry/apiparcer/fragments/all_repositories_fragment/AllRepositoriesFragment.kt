package com.dmitry.apiparcer.fragments.all_repositories_fragment

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.widget.SwipeRefreshLayout
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
    AllRepositoriesView, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var presenter: AllRepositoriesPresenter

    private val loadNextRepositories: PublishSubject<Int> = PublishSubject.create()
    private val goToDetails: PublishSubject<Interactor.RepositoryData> = PublishSubject.create()
    private val refreshData: PublishSubject<Unit> = PublishSubject.create()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_all_repositories, container, false)
    }

    override fun onStart() {
        swipeToRefreshLayout.setOnRefreshListener(this)
        super.onStart()
    }

    override fun loadingIntent(): Observable<Unit> = Observable.merge(Observable.just(Unit), refreshData)

    override fun goToDetails(): Observable<Interactor.RepositoryData> = goToDetails

    override fun loadNext(): Observable<Int> = loadNextRepositories

    override fun refreshDataRepositories(): Observable<Unit> = refreshData

    override fun render(state: GeneralAllRepositoriesViewState) {
        progressBar.visibility = if (state.isLoading) {
            View.VISIBLE
        } else {
            View.GONE
        }

        recycleView.apply {
            visibility = if (state.isLoading || state.errorCode != R.integer.no_errors_code) {
                View.INVISIBLE
            } else {
                View.VISIBLE
            }

            if (adapter == null) {
                adapter = AllRepositoryAdapter(goToDetails, emptyList(), loadNextRepositories)
                layoutManager = LinearLayoutManager(this.context)
            } else {

                if (!state.isRefreshed) {
                    (adapter as AllRepositoryAdapter).let {
                        val lastIndex = it.getLastIndex()
                        it.addItems(state.repositoryModels)
                        it.notifyItemRangeInserted(lastIndex, state.repositoryModels.count())
                    }
                }
                if (state.isRefreshed) {
                    adapter = AllRepositoryAdapter(goToDetails, state.repositoryModels, loadNextRepositories)
                    swipeToRefreshLayout.isRefreshing = false
                }
            }
        }

        if (state.errorCode != R.integer.no_errors_code) {
            swipeToRefreshLayout.isRefreshing = false
            Snackbar.make(recycleView, getString(state.errorCode), Snackbar.LENGTH_INDEFINITE)
                .setAction("Ok") {
                    //hide snackbar only
                }
                .show()
        }
    }

    override fun createPresenter(): AllRepositoriesPresenter {
        activity?.app?.component?.inject(this)
        return presenter
    }

    override fun onRefresh() {
        swipeToRefreshLayout.isRefreshing = true
        refreshData.onNext(Unit)
    }
}