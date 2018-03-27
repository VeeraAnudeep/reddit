package com.ab.reddit

import com.ab.reddit.models.Topic
import com.ab.reddit.models.Topics
import io.reactivex.Single
import retrofit2.http.*


/**
 * Created on 27/03/18.
 */
interface APIService {

    @GET("topics/")
    fun getTopics(): Single<Topics>

    @POST("{type}/")
    fun vote(@Path("type") type : String, @Query("id") id: Int): Single<Topic>

    @FormUrlEncoded
    @POST("add-topic/")
    fun addPost(@Field("content")content:String) : Single<Topic>
}