package com.example.starwars.ui.people.details

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.example.starwars.data.error.CallbackWrapper
import com.example.starwars.data.error.IErrorCallback
import com.example.starwars.data.people.PeopleRepository
import com.example.starwars.data.people.objects.People
import kotlinx.coroutines.launch


class PeopleDetailsScreenModel(): StateScreenModel<PeopleDetailsScreenModel.State>(State.Init), IErrorCallback {

    sealed class State {
        object Init: State()
        data class PeopleItem(val result: People): State()
    }

    fun getPeople(peopleName: String) {
        screenModelScope.launch {
            val response = PeopleRepository.getCachedPeople(peopleName)

            var result =
                object: CallbackWrapper<People?>(this@PeopleDetailsScreenModel, response) {
                    override fun onSuccess(data: People?) {
                        if (data != null) {
                            mutableState.value = State.PeopleItem(result = data)
                        }
                    }

                }
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