package com.ab.reddit.models

import com.google.gson.annotations.SerializedName

/**
 * Created on 27/03/18.
 */
data class Topics (
        @field:SerializedName("data")
        var topics: List<Topic>
)
