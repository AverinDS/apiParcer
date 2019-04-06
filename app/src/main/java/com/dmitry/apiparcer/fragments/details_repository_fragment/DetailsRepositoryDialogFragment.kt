package com.dmitry.apiparcer.fragments.details_repository_fragment

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dmitry.apiparcer.MviDialogFragment
import com.dmitry.apiparcer.R
import com.dmitry.apiparcer.adapters.CommitsAdapter
import com.dmitry.apiparcer.app
import com.dmitry.apiparcer.repositories.Interactor
import com.jakewharton.rxbinding2.support.v7.widget.navigationClicks
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_repository_details.*
import javax.inject.Inject

const val AUTHORS = "AUTHORS"
const val COMMITS = "COMMITS"
const val OWNER = "OWNER"
const val REPOSITORY_NAME = "REPOSITORY_NAME"
const val AUVATAR_URL = "AVATAR_URL"
const val MAX_COMMITS_COUNT = 10


class DetailsRepositoryDialogFragment :
    MviDialogFragment<DetailsRepositoryDialogView, DetailsRepositoryDialogPresenter>(), DetailsRepositoryDialogView {

    @Inject
    lateinit var presenter: DetailsRepositoryDialogPresenter
    var reposName: String = ""
    var owner: String = ""
    var urlAvatar = ""

    private val dataRepositoryPicked: PublishSubject<List<DetailsRepositoryDialogView.AuthorCommit>> =
        PublishSubject.create()

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

    override fun argumentsPicked(): Observable<List<DetailsRepositoryDialogView.AuthorCommit>> = dataRepositoryPicked

    override fun render(state: DetailsRepositoryDialogViewState) {
        toolbar.setNavigationIcon(R.drawable.ic_close_white_24dp)
        toolbar.title = reposName
        when (state) {
            is DetailsRepositoryDialogViewState.RepositoryInfoLoaded -> {
                ownerNameText.text = owner
                repositoryNameText.text = reposName
                progressBar.visibility = View.GONE
                avatarPicture.visibility = View.VISIBLE
                ownerNameText.visibility = View.VISIBLE
                repositoryNameText.visibility = View.VISIBLE
                recycleViewCommits.apply {
                    visibility = View.VISIBLE
                    layoutManager = LinearLayoutManager(this.context)
                    adapter = CommitsAdapter(state.commits)
                }
            }
            is DetailsRepositoryDialogViewState.Error -> {
                progressBar.visibility = View.GONE
                recycleViewCommits.visibility = View.GONE
                avatarPicture.visibility = View.GONE
                ownerNameText.visibility = View.GONE
                repositoryNameText.visibility = View.GONE
                Toast.makeText(this.context, state.message, Toast.LENGTH_LONG).show()
            }
            is DetailsRepositoryDialogViewState.Loading -> {
                progressBar.visibility = View.VISIBLE
                recycleViewCommits.visibility = View.GONE
                avatarPicture.visibility = View.GONE
                ownerNameText.visibility = View.GONE
                repositoryNameText.visibility = View.GONE
            }
            is DetailsRepositoryDialogViewState.Closing -> {
                onDismiss(null)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_repository_details, container, false)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        val authors: List<String>? = arguments?.getStringArrayList(AUTHORS)
        val commits: List<String>? = arguments?.getStringArrayList(COMMITS)

        owner = arguments?.getString(OWNER) ?: ""
        reposName = arguments?.getString(REPOSITORY_NAME) ?: ""
        urlAvatar = arguments?.getString(AUVATAR_URL) ?: ""

        if (urlAvatar.isNotEmpty()) {
            Picasso.get().load(urlAvatar).resize(300, 300).into(avatarPicture)
        }

        if (authors != null && commits != null) {
            dataRepositoryPicked.onNext(createListAuthorCommits(authors, commits))
        }
    }

    private fun createListAuthorCommits(
        authors: List<String>,
        commits: List<String>
    ): List<DetailsRepositoryDialogView.AuthorCommit> {
        val listAuthorCommits = ArrayList<DetailsRepositoryDialogView.AuthorCommit>()
        for (i in 0..authors.lastIndex) {
            listAuthorCommits.add(DetailsRepositoryDialogView.AuthorCommit(authors[i], commits[i]))
        }
        return listAuthorCommits
    }

    companion object {
        fun newInstance(dataRepository: Interactor.RepositoryData): DetailsRepositoryDialogFragment {
            val bundle = Bundle()
            bundle.apply {
                putStringArrayList(
                    AUTHORS,
                    dataRepository.commits.take(MAX_COMMITS_COUNT).map { it.author } as ArrayList)
                putStringArrayList(
                    COMMITS,
                    dataRepository.commits.take(MAX_COMMITS_COUNT).map { it.commitMessage } as ArrayList)
                putString(OWNER, dataRepository.owner.login)
                putString(REPOSITORY_NAME, dataRepository.name)
                putString(AUVATAR_URL, dataRepository.owner.avatarUrl)
            }
            val fragment = DetailsRepositoryDialogFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}