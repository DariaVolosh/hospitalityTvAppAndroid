package com.example.hoteltvapptemplate

import android.app.Application
import android.content.Context
import com.example.hoteltvapptemplate.di.ApplicationComponent
import com.example.hoteltvapptemplate.di.DaggerApplicationComponent
import javax.inject.Inject

class MyApplication @Inject constructor(): Application() {
    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().passContext(applicationContext)
    }
    private lateinit var appContext: Context
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    fun updateContext(newContext: Context) {
        appContext = newContext
    }

    fun getContext() = appContext
}