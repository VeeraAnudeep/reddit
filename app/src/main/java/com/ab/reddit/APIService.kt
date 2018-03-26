package com.ab.reddit

import com.ab.reddit.models.Topics
import retrofit2.http.GET
import rx.Single

/**
 * Created on 27/03/18.
 */
interface APIService {

    @GET("topics/")
    fun getTopics() : Single<Topics>
}