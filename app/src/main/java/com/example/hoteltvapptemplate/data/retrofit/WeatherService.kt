package com.example.hoteltvapptemplate.data.retrofit

import com.example.hoteltvapptemplate.data.dataClasses.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherService {
    @GET("tbilisi")
    fun getWeather(@Query("key") apiKey: String, @Query("unitGroup") unit: String): Call<WeatherResponse>
}
