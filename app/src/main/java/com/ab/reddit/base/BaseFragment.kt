package com.ab.reddit.base

import android.content.Context
import android.support.v4.content.LocalBroadcastManager
import dagger.android.support.DaggerFragment

abstract class BaseFragment<V : BaseViewModel> : DaggerFragment() {

    private var mActivity: BaseActivity<*>? = null

    var localBroadcastManager: LocalBroadcastManager? = null


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity<*>) {
            val activity = context as BaseActivity<*>?
            this.mActivity = activity
            this.localBroadcastManager = activity?.localBroadcastManager
        }
    }

    override fun onDetach() {
        mActivity = null
        super.onDetach()
    }

    fun getBaseActivity(): BaseActivity<*>? {
        return mActivity
    }

    fun hideKeyboard() {
        mActivity?.hideKeyboard()
    }
}