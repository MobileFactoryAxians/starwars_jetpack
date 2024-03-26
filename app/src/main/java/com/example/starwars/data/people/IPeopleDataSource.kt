package com.example.starwars.data.people

import com.example.starwars.data.common.ResultWrapper
import com.example.starwars.data.people.objects.HomeworldListResponse
import com.example.starwars.data.people.objects.People
import com.example.starwars.data.people.objects.PeopleListResponse
import com.example.starwars.data.people.objects.ResultListResponse
import com.example.starwars.data.people.objects.SpecieListResponse

interface IPeopleDataSource {
    //Interfaces required for all objects in this data source.
    interface Common {

    }

    //Interfaces specific to remote data source
    interface Remote : Common {
        suspend fun getPeople() : ResultWrapper<PeopleListResponse>
    }

    //interfaces specific to local data source
    interface Local : Common {
    }

    //interfaces specific to the main repository object. (cache operations, for example). Inherits both Remote and Local as those data sources are accessed by use cases via the repository.
    interface Main : Remote, Local {
        suspend fun getCachedPeople(peopleNAME: String): ResultWrapper<People?>
        suspend fun getSpecie(speciesURL: String): ResultWrapper<SpecieListResponse>
        suspend fun getHomeworld(homeworldURL: String): ResultWrapper<HomeworldListResponse>
    }
}