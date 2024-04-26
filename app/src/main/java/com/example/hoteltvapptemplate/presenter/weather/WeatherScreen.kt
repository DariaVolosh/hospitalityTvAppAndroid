package com.example.hoteltvapptemplate.presenter.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hoteltvapptemplate.R
import com.example.hoteltvapptemplate.ScreenParameters
import com.example.hoteltvapptemplate.data.dataClasses.WeatherHour
import com.example.hoteltvapptemplate.presenter.screen.ScreenBackground

@Composable
fun getCurrentHourWeather(
    hourlyForecast: List<WeatherHour>,
    time: String,
): WeatherHour =
    hourlyForecast.filter {
        it.datetime.contains(time.slice(0..1))
    }[0]

@Composable
fun WeatherScreen(screenParameters: ScreenParameters) {
    val curr = screenParameters.mainScreenViewModels.applicationsViewModel.getContext()
    val updatedContext by remember { mutableStateOf(curr) }

    val weatherViewModel = screenParameters.mainScreenViewModels.weatherViewModel

    val weeklyForecast by weatherViewModel.weeklyForecast.observeAsState()
    val hourlyForecast by weatherViewModel.hourlyForecast.observeAsState()

    val time by screenParameters.mainScreenViewModels.screenViewModel.time.observeAsState()

    val currHourWeather = getCurrentHourWeather(
        hourlyForecast = hourlyForecast ?: emptyList(),
        time = time ?: ""
    )
    val updatedHourWeather by remember {
        mutableStateOf(currHourWeather)
    }

    ScreenBackground(
        screenParameters,
        updatedContext.resources.getString(R.string.weather_forecast),
        updatedContext,
        { modifier ->
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.Bottom
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 15.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = updatedContext.resources.getString(R.string.city_name),
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontSize = 35.sp
                    )

                    Image(
                        modifier = Modifier
                            .padding(horizontal = 15.dp)
                            .size(80.dp),
                        painter = painterResource(
                            getIconResource(updatedHourWeather.icon, true)
                        ),
                        contentDescription = stringResource(R.string.today_forecast)
                    )

                    Text(
                        text = "${updatedHourWeather.temp}°",
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontSize = 50.sp
                    )
                }

                Box(
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Image(
                        modifier = Modifier.fillMaxWidth(),
                        painter = painterResource(R.drawable.weather_wave),
                        contentDescription = stringResource(R.string.weather_wave),
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                        contentScale = ContentScale.FillWidth
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        ForecastRow(
                            Modifier.weight(0.5f),
                            header = updatedContext.resources.getString(R.string.today_forecast),
                            false,
                            hourlyForecast = hourlyForecast ?: emptyList()
                        )

                        ForecastRow(
                            Modifier.weight(0.5f),
                            header = updatedContext.resources.getString(R.string.tomorrow_forecast),
                            true,
                            weeklyForecast = weeklyForecast ?: emptyList()
                        )
                    }
                }
            }
        }
    ) {}
}