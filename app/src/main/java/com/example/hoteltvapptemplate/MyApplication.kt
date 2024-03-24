package com.example.hoteltvapptemplate

import android.app.Application
import com.example.hoteltvapptemplate.di.ApplicationComponent
import com.example.hoteltvapptemplate.di.DaggerApplicationComponent

class MyApplication: Application() {
    val appComponent: ApplicationComponent = DaggerApplicationComponent.create()
}