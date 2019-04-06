package com.dmitry.apiparcer


import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View
import com.hannesdorfmann.mosby3.FragmentMviDelegate
import com.hannesdorfmann.mosby3.FragmentMviDelegateImpl
import com.hannesdorfmann.mosby3.MviDelegateCallback
import com.hannesdorfmann.mosby3.mvi.MviPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView

/*
Kotlin adaptation of MviDialogFragment from mosby (absent in stable version)
 */

abstract class MviDialogFragment<V : MvpView, P : MviPresenter<V, *>> : DialogFragment(), MvpView,
    MviDelegateCallback<V, P> {

    private var _isRestoringViewState = false
    private var _mvpDelegate: FragmentMviDelegate<V, P>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getMvpDelegate().onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        getMvpDelegate().onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        getMvpDelegate().onSaveInstanceState(outState)
    }

    override fun onPause() {
        super.onPause()
        getMvpDelegate().onPause()
    }

    override fun onResume() {
        super.onResume()
        getMvpDelegate().onResume()
    }

    override fun onStart() {
        super.onStart()
        getMvpDelegate().onStart()
    }

    override fun onStop() {
        super.onStop()
        getMvpDelegate().onStop()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMvpDelegate().onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        getMvpDelegate().onDestroyView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getMvpDelegate().onActivityCreated(savedInstanceState)
    }

//    override fun onAttach(activity: Activity) {
//        super.onAttach(activity)
//        getMvpDelegate().onAttach(activity)
//    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getMvpDelegate().onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
        getMvpDelegate().onDetach()
    }

    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)
        getMvpDelegate().onAttachFragment(childFragment)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        getMvpDelegate().onConfigurationChanged(newConfig)
    }

    abstract override fun createPresenter(): P

    private fun getMvpDelegate(): FragmentMviDelegate<V, P> {
        if (_mvpDelegate == null) {
            _mvpDelegate = FragmentMviDelegateImpl(this, this)
        }

        return _mvpDelegate as FragmentMviDelegate<V, P>
    }

    override fun getMvpView(): V {
        try {
            return this as V
        } catch (e: ClassCastException) {
            val msg =
                "Couldn't cast the View to the corresponding View interface. Most likely you forgot to add \"Fragment implements YourMvpViewInterface\".\""
            Log.e(this.toString(), msg)
            throw RuntimeException(msg, e)
        }
    }

    override fun setRestoringViewState(restoringViewState: Boolean) {
        _isRestoringViewState = restoringViewState
    }

    fun isRestoringViewState() = _isRestoringViewState
}