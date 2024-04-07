package com.example.hoteltvapptemplate.presenter.weather

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hoteltvapptemplate.R

@Composable
fun Forecast(
    header: String,
    @DrawableRes icon: Int,
    temperature: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = header,
            fontSize = 20.sp
        )

        Image(
            modifier = Modifier
                .size(55.dp)
                .padding(vertical = 5.dp),
            painter = painterResource(icon),
            contentDescription = stringResource(R.string.weather_icon),
        )

        Text(
            text = temperature,
            fontSize = 20.sp
        )
    }
}