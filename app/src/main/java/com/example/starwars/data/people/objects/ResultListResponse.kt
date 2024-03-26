package com.example.starwars.data.people.objects

import com.google.gson.annotations.SerializedName

data class ResultListResponse(
    @SerializedName("count") val count : Int,
    @SerializedName("next") val next : String,
    @SerializedName("previous") val previous : String,
    @SerializedName("results") val list : PeopleListResponse
)
