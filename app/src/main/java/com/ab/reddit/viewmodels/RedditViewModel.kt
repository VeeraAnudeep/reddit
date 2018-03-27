package com.ab.reddit.viewmodels

import android.arch.lifecycle.MutableLiveData
import com.ab.reddit.APIService
import com.ab.reddit.base.BaseViewModel
import com.ab.reddit.models.Topic
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

/**
 * Created on 27/03/18.
 */
class RedditViewModel(private val retrofit: Retrofit) : BaseViewModel() {

    var topics = MutableLiveData<ArrayList<Topic>>()
    var showZeroCase = MutableLiveData<Boolean>()
    var errorCase = MutableLiveData<Boolean>()
    var onVoted = MutableLiveData<Topic>()
    var onPostSubmitted = MutableLiveData<Boolean>()
    private var topicList: ArrayList<Topic>? = null

    fun getTopics() {
        getCompositeDisposable().add(retrofit.create(APIService::class.java).getTopics()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.topics.isNotEmpty()) {
                        showZeroCase.value = false
                        topicList = it.topics as ArrayList<Topic>?
                        topics.value = topicList
                    } else {
                        showZeroCase.value = true
                    }
                }, {
                    errorCase.value = true
                }))
    }

    fun vote(position: Int, type: String) {
        if (position < 0) {
            return
        }
        val id: Int = topicList?.get(position)?.id ?: 0
        if (id > 0) {
            getCompositeDisposable().add(retrofit.create(APIService::class.java).vote(type, id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        topicList?.set(position, it)
                        onVoted.value = it
                        topics.value = topicList
                    }, {
                        errorCase.value = true
                    }))
        }
    }

    fun post(content: String) {
        getCompositeDisposable().add(retrofit.create(APIService::class.java).addPost(content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    onPostSubmitted.value = true
                }, {
                    onPostSubmitted.value = false
                }))
    }
}