package com.example.starwars

import android.app.Application
import android.content.Context

class MyApplication: Application() {

    companion object {
        private lateinit var sInstance: MyApplication

        fun getAppContext(): Context {
            return sInstance.applicationContext
        }

        //Production API
        val BASE_URL = "https://swapi.py4e.com/api/"
    }

    override fun onCreate() {
        super.onCreate()
        sInstance = this
    }
}