package com.example.hoteltvapptemplate.di

import com.example.hoteltvapptemplate.data.retrofit.WeatherService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {
    @Singleton
    private fun provideRetrofit() =
        Retrofit.Builder()
            .baseUrl("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideWeatherService() =
        provideRetrofit().create(WeatherService::class.java)
}