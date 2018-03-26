package com.toppr.dubbio

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.toppr.dubbio.v3.base.BaseViewModel

class ViewModelProviderFactory<V : Any>(private val viewModel: V) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BaseViewModel::class.java)) {
            return viewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}