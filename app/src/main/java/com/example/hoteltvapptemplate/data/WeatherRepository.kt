package com.example.hoteltvapptemplate.data

import com.example.hoteltvapptemplate.data.retrofit.WeatherService
import kotlinx.coroutines.CompletableDeferred
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherService: WeatherService
) {
    suspend fun getWeeklyForecast(): List<WeatherDay> {
        val deferred = CompletableDeferred<List<WeatherDay>>()

        weatherService.getWeather(WEATHER_API_KEY, "metric").enqueue(
            object: Callback<WeatherResponse> {
                override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                    if (response.isSuccessful) {
                        deferred.complete(response.body()?.days ?: emptyList())
                    } else {
                        deferred.completeExceptionally(Throwable(
                            "Failed to get weather forecast data"
                        ))
                    }
                }

                override fun onFailure(call: Call<WeatherResponse>, throwable: Throwable) {
                    deferred.completeExceptionally(throwable)
                }
            }
        )

        return deferred.await()
    }
}