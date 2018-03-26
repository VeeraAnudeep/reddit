package com.toppr.dubbio.v3.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    private var repository: BaseRepository? = null
    private var internetConnectedLiveData = MutableLiveData<Boolean>()
    var errorMsg = MutableLiveData<String>()


    fun setInternetLiveData(isConnected: Boolean) {
        internetConnectedLiveData.value = isConnected
    }


    private var compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getCompositeDisposable(): CompositeDisposable {
        return compositeDisposable
    }

}