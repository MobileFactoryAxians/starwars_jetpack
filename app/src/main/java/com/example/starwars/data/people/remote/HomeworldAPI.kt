package com.example.starwars.data.people.remote

import com.example.starwars.data.people.objects.HomeworldListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeworldAPI {
    @GET("planets/{url}")
    suspend fun getHomeworld(@Path("url") homeworldURL: String) : HomeworldListResponse
}