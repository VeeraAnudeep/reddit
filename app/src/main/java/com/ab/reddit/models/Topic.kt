package com.ab.reddit.models

import com.google.gson.annotations.SerializedName

/**
 * Created on 27/03/18.
 */
data class Topic(
        @field:SerializedName("id")
        var id : Int,

        @field:SerializedName("content")
        var content : String,

        @field:SerializedName("n_upvotes")
        var nUpVotes : Int,

        @field:SerializedName("n_downvotes")
        var nDownVotes : Int
)