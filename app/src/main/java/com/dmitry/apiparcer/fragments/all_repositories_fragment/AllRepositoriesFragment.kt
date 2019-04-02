package com.dmitry.apiparcer.fragments.all_repositories_fragment

import com.dmitry.apiparcer.app
import com.hannesdorfmann.mosby3.mvi.MviFragment
import javax.inject.Inject

class AllRepositoriesFragment : MviFragment<AllRepositoriesViewFragment, AllRepositoriesPresenter>() {

    @Inject
    lateinit var presenter: AllRepositoriesPresenter

    override fun createPresenter(): AllRepositoriesPresenter {
        activity?.app?.component?.inject(this)
        return presenter
    }

}