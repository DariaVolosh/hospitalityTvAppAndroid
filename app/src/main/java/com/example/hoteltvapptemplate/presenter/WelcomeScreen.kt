package com.example.hoteltvapptemplate.presenter

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hoteltvapptemplate.ui.theme.md_theme_transparent
import com.example.hoteltvapptemplate.R

@Composable
fun WelcomeScreen() {
    val brush = Brush.verticalGradient(
        listOf(
            MaterialTheme.colorScheme.primaryContainer,
            md_theme_transparent
        )
    )

    Box {
        Image(
            painter = painterResource(R.drawable.hotel_image),
            contentDescription = stringResource(R.string.hotel_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .drawWithCache {
                    onDrawWithContent {
                        drawContent()
                        drawRect(brush)
                    }
                }
        )
        
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .padding(30.dp, 40.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        fontSize = 40.sp,
                        text = stringResource(R.string.welcome_to_our_hotel),
                        fontFamily = FontFamily(Font(R.font.nokoro_regular)),
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        modifier = Modifier.padding(top = 10.dp),
                        fontSize = 25.sp,
                        text = stringResource(R.string.we_wish_you_a_pleasant_stay),
                        fontFamily = FontFamily(Font(R.font.nokora_light)),
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }

                Column {
                    Text(
                        fontSize = 35.sp,
                        text = stringResource(R.string.current_time),
                        fontFamily = FontFamily(Font(R.font.nokora_light)),
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        fontSize = 20.sp,
                        text = "11/02/2024",
                        fontFamily = FontFamily(Font(R.font.nokora_light)),
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }

            CategoriesRow()
        }
    }
}