package com.example.hoteltvapptemplate.presenter.restaurants

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hoteltvapptemplate.R

@Composable
fun RestaurantCard(
    restaurantName: String,
    restaurantDescription: String,
    openHours: String,
    photo: Int,
    modifier: Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(0.7f)
            .padding(bottom = 15.dp)
            .background(Color.White)
            .padding(15.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            painter = painterResource(photo),
            contentDescription = stringResource(R.string.restaurant_image),
            modifier = Modifier
                .fillMaxHeight()
                .padding(end = 25.dp)
                .weight(0.5f)
        )

        Column(
            modifier = Modifier
                .weight(0.5f)
                .padding(end = 40.dp)
        ) {
            Text(
                text = restaurantName,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 30.sp,
                fontFamily = FontFamily(Font(R.font.nokoro_regular))
            )

            HorizontalDivider(
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth(0.7f)
                    .align(Alignment.End)
            )

            Text(
                modifier = Modifier
                    .padding(top = 15.dp),
                text = restaurantDescription,
                color = MaterialTheme.colorScheme.surfaceVariant,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.nokora_light))
            )

            Spacer(
                modifier = Modifier.weight(1f)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Open hours: $openHours",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.nokoro_regular)),
                )

                Image(
                    modifier = Modifier
                        .size(30.dp)
                        .padding(start = 10.dp),
                    painter = painterResource(R.drawable.open_hours),
                    contentDescription = stringResource(R.string.open_horus)
                )
            }
        }
    }
}