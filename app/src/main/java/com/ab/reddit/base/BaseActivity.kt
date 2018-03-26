package com.toppr.dubbio.v3.base

import android.content.Context
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.view.inputmethod.InputMethodManager
import com.google.gson.Gson
import dagger.android.support.DaggerAppCompatActivity
import java.io.File
import javax.inject.Inject


/**
 * All activities should extend from this class
 */
abstract class BaseActivity<out T : BaseViewModel> : DaggerAppCompatActivity() {

    private var mViewModel: T? = null

    @Inject
    lateinit var localBroadcastManager: LocalBroadcastManager
    @Inject
    lateinit var gson: Gson


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.mViewModel = if (mViewModel == null) getViewModel() else mViewModel
    }

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract fun getViewModel(): T


    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}