package com.dmitry.apiparcer.fragments.details_repository_fragment

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dmitry.apiparcer.MviDialogFragment
import com.dmitry.apiparcer.R
import com.dmitry.apiparcer.app
import com.jakewharton.rxbinding2.support.v7.widget.navigationClicks
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_repository_details.*
import javax.inject.Inject

class DetailsRepositoryDialogFragment :
    MviDialogFragment<DetailsRepositoryDialogView, DetailsRepositoryDialogPresenter>(), DetailsRepositoryDialogView {

    @Inject
    lateinit var presenter: DetailsRepositoryDialogPresenter

    override fun createPresenter(): DetailsRepositoryDialogPresenter {
        activity?.app?.component?.inject(this)
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun closingIntent(): Observable<Unit> = toolbar.navigationClicks()

    override fun loadingIntent(): Observable<Unit> = Observable.just(Unit)

    override fun render(state: DetailsRepositoryDialogViewState) {
        toolbar.setNavigationIcon(R.drawable.ic_close_white_24dp)
        toolbar.title = "Details"
        when (state) {
            is DetailsRepositoryDialogViewState.RepositoryInfoLoaded -> {

            }
            is DetailsRepositoryDialogViewState.Error -> {

            }
            is DetailsRepositoryDialogViewState.Loading -> {
                progressBar.visibility = View.VISIBLE
            }
            else -> {

            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_repository_details, container, false)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }
}