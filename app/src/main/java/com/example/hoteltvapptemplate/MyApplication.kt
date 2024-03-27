package com.example.hoteltvapptemplate

import android.app.Application
import android.content.Context
import com.example.hoteltvapptemplate.di.ApplicationComponent
import com.example.hoteltvapptemplate.di.DaggerApplicationComponent

class MyApplication: Application() {
    val appComponent: ApplicationComponent = DaggerApplicationComponent.create()
    private lateinit var appContext: Context
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    fun updateContext(newContext: Context) {
        appContext = newContext
    }
}