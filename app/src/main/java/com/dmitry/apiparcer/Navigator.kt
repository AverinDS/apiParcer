package com.dmitry.apiparcer

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.dmitry.apiparcer.fragments.all_repositories_fragment.AllRepositoriesFragment
import com.dmitry.apiparcer.fragments.details_repository_fragment.DetailsRepositoryDialogFragment
import com.dmitry.apiparcer.repositories.Interactor
import java.lang.ref.WeakReference

class Navigator {
    private var fragmentManager: WeakReference<FragmentManager>? = null

    fun initialize(fragmentManager: FragmentManager) {
        this.fragmentManager = WeakReference(fragmentManager)
    }

    fun showAllRepositoriesFragment() {
        showFragment(AllRepositoriesFragment())
    }

    fun showDetailsRepository(repositoryData: Interactor.RepositoryData) {
        val detailsFragment = DetailsRepositoryDialogFragment()
        fragmentManager?.get()?.let { fragmentManager ->
            detailsFragment.show(fragmentManager, "SD")
        }
    }

    private fun showFragment(fragment: Fragment) {
        fragmentManager?.get()?.beginTransaction()?.replace(R.id.container, fragment)?.commit()
    }
}