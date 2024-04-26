package com.example.hoteltvapptemplate

import android.app.Application
import android.content.Context
import android.util.Log
import com.example.hoteltvapptemplate.di.ApplicationComponent
import com.example.hoteltvapptemplate.di.DaggerApplicationComponent

class MyApplication: Application() {
    private lateinit var appContext: Context
    val appComponent: ApplicationComponent.Factory by lazy {
        DaggerApplicationComponent.factory()
    }
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        Log.i("LOL", this.toString())
    }

    fun updateContext(newContext: Context) {
        appContext = newContext
    }

    fun getContext(): Context {
        Log.i("LOL", this.toString())
        return appContext
    }
}