package com.example.starwars.data.people.remote

import com.example.starwars.data.people.objects.SpecieListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface SpecieAPI {
    @GET("species/{url}")
    suspend fun getSpecie(@Path("url") specieURL: String) : SpecieListResponse
}