package com.example.hoteltvapptemplate.di

import com.example.hoteltvapptemplate.MainActivity
import dagger.Component


@Component
interface ApplicationComponent {
    fun inject(activity: MainActivity)
}