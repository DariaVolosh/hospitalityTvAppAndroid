package com.example.hoteltvapptemplate.presenter

import android.content.Context
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hoteltvapptemplate.R
import com.example.hoteltvapptemplate.ui.theme.md_theme_transparent

@Composable
fun ScreenBackground(
    screenViewModel: ScreenViewModel,
    updatedContext: Context,
    composable: @Composable () -> Unit
) {
    val date = screenViewModel.date.observeAsState()
    val time = screenViewModel.time.observeAsState()

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
                    .padding(top = 30.dp, start = 40.dp, end = 40.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        modifier = Modifier.fillMaxWidth(0.5f),
                        fontSize = 40.sp,
                        text = updatedContext.resources.getString(R.string.welcome_to_our_hotel),
                        fontFamily = FontFamily(Font(R.font.nokoro_regular)),
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        lineHeight = TextUnit(50f, TextUnitType.Sp)
                    )

                    composable()
                }

                Column {
                    Text(
                        fontSize = 35.sp,
                        text = time.value ?: "",
                        fontFamily = FontFamily(Font(R.font.nokora_light)),
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        fontSize = 20.sp,
                        text = date.value ?: "",
                        fontFamily = FontFamily(Font(R.font.nokora_light)),
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }

            CategoriesRow(updatedContext)
        }
    }
}