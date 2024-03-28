package com.example.starwars.ui.people.details

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.example.starwars.data.error.CallbackWrapper
import com.example.starwars.data.error.IErrorCallback
import com.example.starwars.data.people.PeopleRepository
import com.example.starwars.data.people.objects.People
import com.example.starwars.data.people.remote.HomeworldAPI
import kotlinx.coroutines.launch


class PeopleDetailsScreenModel(): StateScreenModel<PeopleDetailsScreenModel.State>(State.Init), IErrorCallback {

    sealed class State {
        object Init: State()
        data class PeopleItem(
            val result: People,
            val specie: String?,
            val homeworld: String?
        ): State()
    }

    fun getPeople(peopleName: String) {
        screenModelScope.launch {
            val response = PeopleRepository.getCachedPeople(peopleName)

            var result =
                object: CallbackWrapper<People?>(this@PeopleDetailsScreenModel, response) {
                    override fun onSuccess(data: People?) {
                        if (data != null) {
                            screenModelScope.launch {
                                val specie = getSpeciesDetail(data.species.get(0))
                                val homeworld = getHomeworldDetail(data.homeworld)

                                mutableState.value = State.PeopleItem(
                                    result = data,
                                    specie = specie,
                                    homeworld = homeworld
                                )
                            }
                        }
                    }

                }
        }
    }

    private suspend fun getSpeciesDetail(speciesURL: String): String? {
        return speciesURL.let { url ->
            val speciesResponse = PeopleRepository.getSpecie(url)
            speciesResponse.result?.name
        }
    }

    private suspend fun getHomeworldDetail(homeworldURL: String): String? {
        return homeworldURL.let { url ->
            val homeworldResponse = PeopleRepository.getHomeworld(url)
            homeworldResponse.result?.name
        }
    }

    override fun onGenericError(
        message: String?,
        validationErrors: Map<String, ArrayList<String>>?
    ) {
        TODO("Not yet implemented")
    }

    override fun onTimeout() {
        TODO("Not yet implemented")
    }

    override fun onNetworkError() {
        TODO("Not yet implemented")
    }

    override fun onSessionExpired() {
        TODO("Not yet implemented")
    }
}