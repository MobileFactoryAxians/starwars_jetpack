package com.example.starwars.ui.main

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.example.starwars.data.error.CallbackWrapper
import com.example.starwars.data.error.IErrorCallback
import com.example.starwars.data.people.PeopleRepository
import com.example.starwars.data.people.objects.People
import com.example.starwars.data.people.objects.PeopleListResponse
import kotlinx.coroutines.launch

class MainScreenModel(): StateScreenModel<MainScreenModel.State>(State.Init), IErrorCallback {

    var llState: LazyListState by mutableStateOf(LazyListState(0,0))

    sealed class State {
        object Init: State()
        object Loading: State()
        data class PeopleList(val results: List<People>): State()
    }

    fun getPeople() {
        mutableState.value = State.Loading
        screenModelScope.launch {
            val peopleResponse = PeopleRepository.getPeople()
            var result =
                object: CallbackWrapper<PeopleListResponse>(this@MainScreenModel, peopleResponse) {
                    override fun onSuccess(data: PeopleListResponse) {
                        if (data != null) {
                            mutableState.value = State.PeopleList(results = data)
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