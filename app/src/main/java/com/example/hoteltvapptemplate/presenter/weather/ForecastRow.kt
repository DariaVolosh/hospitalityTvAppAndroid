package com.example.hoteltvapptemplate.presenter.weather

import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.unit.dp
import com.example.hoteltvapptemplate.R
import com.example.hoteltvapptemplate.data.WeatherDay
import com.example.hoteltvapptemplate.data.WeatherHour
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

fun parseDatetimeToDayAndMonth(datetime: String): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = sdf.parse(datetime)

    val formattedSdf = SimpleDateFormat("dd MMM", Locale.getDefault())
    return date?.let { formattedSdf.format(it) } ?: ""
}

fun parseTimestampToHoursAndMins(datetime: String): String {
    val sdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    val date = sdf.parse(datetime)

    val formattedSdf = SimpleDateFormat("HH:mm", Locale.getDefault())
    return date?.let { formattedSdf.format(it) } ?: ""
}

fun getIconResource(icon: String, colorful: Boolean): Int {
    return when (icon) {
        "rain" -> if (colorful) R.drawable.rainy_colorful else R.drawable.rainy
        "partly-cloudy-day" -> if (colorful) R.drawable.partly_cloudy_colorful else R.drawable.partly_cloudy
        "cloudy" -> if (colorful) R.drawable.cloudy_colorful else R.drawable.cloudy
        else -> if (colorful) R.drawable.sunny_colorful else R.drawable.sunny
    }
}
@Composable
fun ForecastRow(
    modifier: Modifier,
    header: String,
    weekly: Boolean,
    weeklyForecast: List<WeatherDay> = emptyList(),
    hourlyForecast: List<WeatherHour> = emptyList()
) {
    val scrollState = rememberLazyListState()

    val interactionSource = remember { MutableInteractionSource() }
    var currentWeather = 0
    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = header)

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            LazyRow(
                state = scrollState,
                modifier = Modifier
                    .padding(horizontal = 25.dp, vertical = 15.dp)
                    .fillMaxWidth()
                    .focusable(interactionSource = interactionSource)
                    .onKeyEvent {
                        when (it.key) {
                            Key.DirectionRight -> {
                                if (
                                    weekly && (currentWeather + 5) < weeklyForecast.size ||
                                    !weekly && (currentWeather + 5) < hourlyForecast.size
                                ) {
                                    scope.launch {
                                        scrollState.animateScrollToItem(currentWeather + 5)
                                        currentWeather += 5
                                    }

                                    true
                                } else {
                                    false
                                }
                            }

                            Key.DirectionLeft -> {
                                if (currentWeather - 5 >= 0) {
                                    scope.launch {
                                        scrollState.animateScrollToItem(currentWeather - 5)
                                        currentWeather -= 5
                                    }
                                    true
                                } else {
                                    false
                                }
                            }

                            else -> false
                        }
                    },
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
               if (
                   weekly && weeklyForecast.isNotEmpty() ||
                   !weekly && hourlyForecast.isNotEmpty()
                   ) {
                   items(
                       if (weekly) weeklyForecast.size
                       else hourlyForecast.size
                   ) {index ->
                       Forecast(
                           header =
                            if (weekly) {
                                parseDatetimeToDayAndMonth(weeklyForecast[index].datetime)
                            } else {
                                parseTimestampToHoursAndMins(hourlyForecast[index].datetime)
                            },

                           icon = if (weekly) {
                               getIconResource(weeklyForecast[index].icon, false)
                           } else {
                               getIconResource(hourlyForecast[index].icon, false)
                           },

                           temperature =
                               if (weekly) {
                                   "${weeklyForecast[index].tempMax}°"
                               } else {
                                   "${hourlyForecast[index].temp}°"
                               }
                       )
                   }
               }
            }

            VerticalDivider(
                modifier = Modifier.height(80.dp)
            )
        }
    }
}