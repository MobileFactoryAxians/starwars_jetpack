package com.example.starwars.data.people.remote

import com.example.starwars.data.common.HTTPClient
import com.example.starwars.data.common.ResultWrapper
import com.example.starwars.data.people.IPeopleDataSource
import com.example.starwars.data.people.objects.HomeworldListResponse
import com.example.starwars.data.people.objects.People
import com.example.starwars.data.people.objects.PeopleListResponse
import com.example.starwars.data.people.objects.ResultListResponse
import com.example.starwars.data.people.objects.SpecieListResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object PeopleRemoteDataSource: IPeopleDataSource.Main {

    private val peopleAPI = HTTPClient(PeopleAPI::class.java).get()
    private val specieAPI = HTTPClient(SpecieAPI::class.java).get()
    private val homeworldAPI = HTTPClient(HomeworldAPI::class.java).get()
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun getCachedPeople(peopleNAME: String): ResultWrapper<People?> {
        TODO("Not yet implemented")
    }

    override suspend fun getSpecie(speciesURL: String): ResultWrapper<SpecieListResponse> {
        return ResultWrapper.safeApiCall(dispatcher) {
            specieAPI.getSpecie(speciesURL.substringAfter("species/"))
        }
    }

    override suspend fun getHomeworld(homeworldURL: String): ResultWrapper<HomeworldListResponse> {
        return ResultWrapper.safeApiCall(dispatcher) {
            homeworldAPI.getHomeworld(homeworldURL.substringAfter("planets/"))
        }
    }

    override suspend fun getPeople(): ResultWrapper<PeopleListResponse> {
        return ResultWrapper.safeApiCall(dispatcher) {
            (peopleAPI.getPeople()).list
        }
    }
}