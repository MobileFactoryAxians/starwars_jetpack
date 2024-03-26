package com.example.starwars.ui.common

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.starwars.MyApplication
import com.example.starwars.R
import com.example.starwars.data.error.IErrorCallback

abstract class BaseViewModel(application: Application) : AndroidViewModel(application),
    IErrorCallback {

    val isRefreshing = MutableLiveData<Boolean>().apply { value = false }
    val isLoading = MutableLiveData<Boolean>().apply { value = false }
    val noDataAvailable = MutableLiveData<Boolean>().apply { value = false }
    val errorMessage = MutableLiveData<String?>()

    abstract fun onError(message: String? = null, validationErrors: Map<String, ArrayList<String>>? = null)

    override fun onGenericError(message: String?, validationErrors: Map<String, ArrayList<String>>?) {
        if (validationErrors == null) {
            errorMessage.postValue(message)
        }
        onError(message, validationErrors)
    }

    override fun onTimeout() {
        // API Call Timeout :(
        errorMessage.postValue(MyApplication.getAppContext().getString(R.string.error_network_server_timeout))
        onError()
    }

    override fun onNetworkError() {
        // Check your connection :(
        errorMessage.postValue(MyApplication.getAppContext().getString(R.string.error_network_error))
        onError()
    }

    override fun onSessionExpired() {
    }
}