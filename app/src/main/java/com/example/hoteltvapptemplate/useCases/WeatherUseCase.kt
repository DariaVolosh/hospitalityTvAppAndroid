package com.example.hoteltvapptemplate.useCases

import com.example.hoteltvapptemplate.data.WeatherRepository
import javax.inject.Inject

class WeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend fun getWeeklyForecast() = weatherRepository.getWeeklyForecast()
}