package com.dmitry.apiparcer.fragments.all_repositories_fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

    @Inject
    lateinit var presenter: AllRepositoriesPresenter

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
        when (state) {
            is AllRepositoriesViewState.LoadRepositories -> {
                progressBar.visibility = View.GONE

                recycleView.apply {
                    visibility = View.VISIBLE
                    layoutManager = LinearLayoutManager(this.context)

                    if (adapter == null) {
                        adapter = AllRepositoryAdapter(state.repositoryModels)
                    } else {
                        (adapter as AllRepositoryAdapter).let {
                            val lastIndex = it.getLastIndex()
                            it.addItems(state.repositoryModels)
                            it.notifyItemRangeInserted(lastIndex, state.repositoryModels.count())
                        }
                    }
                }
            }
            is Error -> {
                progressBar.visibility = View.GONE
                Toast.makeText(this.context, state.message, Toast.LENGTH_LONG).show()
            }
            is AllRepositoriesViewState.Loading -> {
                progressBar.visibility = View.VISIBLE
                recycleView.visibility = View.INVISIBLE
            }
        }
    }

    override fun createPresenter(): AllRepositoriesPresenter {
        activity?.app?.component?.inject(this)
        return presenter
    }

}