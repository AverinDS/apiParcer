package com.dmitry.apiparcer

import android.support.v4.app.FragmentManager
import java.lang.ref.WeakReference

class Navigator {
    private var fragmentManager: WeakReference<FragmentManager>? = null

    fun initialize(fragmentManager: FragmentManager) {
        this.fragmentManager = WeakReference(fragmentManager)
    }

}