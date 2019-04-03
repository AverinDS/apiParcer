package com.dmitry.apiparcer

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.dmitry.apiparcer.fragments.all_repositories_fragment.AllRepositoriesFragment
import java.lang.ref.WeakReference

class Navigator {
    private var fragmentManager: WeakReference<FragmentManager>? = null

    fun initialize(fragmentManager: FragmentManager) {
        this.fragmentManager = WeakReference(fragmentManager)
    }

    fun showAllRepositoriesFragment() {
        showFragment(AllRepositoriesFragment())
    }

    private fun showFragment(fragment: Fragment) {
        fragmentManager?.get()?.beginTransaction()?.replace(R.id.container, fragment)?.commit()
    }
}