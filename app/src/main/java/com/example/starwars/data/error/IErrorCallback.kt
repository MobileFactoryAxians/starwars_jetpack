package com.example.starwars.data.error

interface IErrorCallback {
    //fun onHTTPError(message : String?, validationErrors: Map<String, ArrayList<String>>? = null)
    fun onGenericError(message: String?, validationErrors: Map<String, ArrayList<String>>?)
    fun onTimeout()
    fun onNetworkError()
    fun onSessionExpired()
}