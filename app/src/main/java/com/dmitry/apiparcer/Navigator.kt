package com.dmitry.apiparcer

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.dmitry.apiparcer.fragments.all_repositories_fragment.AllRepositoriesFragment
import com.dmitry.apiparcer.fragments.details_repository_fragment.DetailsRepositoryDialogFragment
import com.dmitry.apiparcer.repositories.Interactor
import java.lang.ref.WeakReference

const val DETAILS_FRAGMENT_DIALOG_TAG = "DETAILS_FRAGMENT_DIALOG_TAG"
const val ALL_REPOSITORIES_TAG = "ALL_REPOSITORIES_TAG"

class Navigator {
    private var fragmentManager: WeakReference<FragmentManager>? = null

    fun initialize(fragmentManager: FragmentManager) {
        this.fragmentManager = WeakReference(fragmentManager)
    }

    fun showAllRepositoriesFragment() {
        showFragment(AllRepositoriesFragment(), addToBackStack = true, backStackTag = ALL_REPOSITORIES_TAG)
    }

    fun showDetailsRepository(repositoryData: Interactor.RepositoryData) {
        val detailsFragment = DetailsRepositoryDialogFragment.newInstance(repositoryData)
        fragmentManager?.get()?.let { fragmentManager ->
            detailsFragment.show(fragmentManager, DETAILS_FRAGMENT_DIALOG_TAG)
        }
    }

    fun popBackStack() {
        fragmentManager?.get()?.popBackStack()
    }

    private fun showFragment(fragment: Fragment, addToBackStack: Boolean, backStackTag: String? = "") {
        val fragmentTransaction = fragmentManager?.get()?.beginTransaction()?.replace(R.id.container, fragment)

        if (addToBackStack) {
            fragmentTransaction?.addToBackStack(backStackTag)
        }

        fragmentTransaction?.commit()

    }
}