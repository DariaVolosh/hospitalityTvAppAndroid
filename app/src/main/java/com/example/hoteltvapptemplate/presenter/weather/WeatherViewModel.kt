package com.example.hoteltvapptemplate.presenter.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hoteltvapptemplate.data.WeatherDay
import com.example.hoteltvapptemplate.data.WeatherHour
import com.example.hoteltvapptemplate.useCases.WeatherUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
    private val weatherUseCase: WeatherUseCase
): ViewModel() {
    val weeklyForecast = MutableLiveData<List<WeatherDay>>()
    val hourlyForecast = MutableLiveData<List<WeatherHour>>()

    init {
        viewModelScope.launch {
            val days = weatherUseCase.getWeeklyForecast()
            weeklyForecast.postValue(days)
            hourlyForecast.postValue(days[0].hours)
        }
    }
}