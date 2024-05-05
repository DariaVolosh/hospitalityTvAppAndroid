package com.example.hoteltvapptemplate.di

import android.content.Context
import com.example.hoteltvapptemplate.MainActivity
import com.example.hoteltvapptemplate.MyApplication
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        RetrofitModule::class,
        AmazonWebServicesModule::class
    ]
)
@Singleton
interface ApplicationComponent {
    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {
        fun passAppAndContext(
            @BindsInstance context: Context,
            @BindsInstance myApplication: MyApplication
        ): ApplicationComponent
    }
}