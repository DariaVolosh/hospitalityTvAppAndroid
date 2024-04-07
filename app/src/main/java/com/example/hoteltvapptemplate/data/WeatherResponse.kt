package com.example.hoteltvapptemplate.data

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    val days: List<WeatherDay>
)

data class WeatherDay(
    val datetime: String,
    val icon: String,
    val hours: List<WeatherHour>,
    @SerializedName("tempmax") val tempMax: Float
)

data class WeatherHour(
    val datetime: String = "",
    val icon: String = "",
    val temp: Float = 0.0f
)