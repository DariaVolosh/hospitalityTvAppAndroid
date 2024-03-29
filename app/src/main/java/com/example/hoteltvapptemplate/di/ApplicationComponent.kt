package com.example.hoteltvapptemplate.di

import android.content.Context
import com.example.hoteltvapptemplate.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component
@Singleton
interface ApplicationComponent {
    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {
        fun passContext(@BindsInstance context: Context): ApplicationComponent
    }
}