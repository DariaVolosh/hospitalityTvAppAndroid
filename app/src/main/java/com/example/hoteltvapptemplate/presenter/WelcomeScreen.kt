package com.example.hoteltvapptemplate.presenter

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hoteltvapptemplate.ui.theme.md_theme_transparent
import com.example.hoteltvapptemplate.R
import java.util.Locale

fun setLocale(locale: Locale, oldContext: Context): Context {
    val config = Configuration(oldContext.resources.configuration)
    config.setLocale(locale)
    return oldContext.createConfigurationContext(config)
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun WelcomeScreen() {
    val brush = Brush.verticalGradient(
        listOf(
            MaterialTheme.colorScheme.primaryContainer,
            md_theme_transparent
        )
    )

    val curr = LocalContext.current
    var updatedContext by remember { mutableStateOf(curr) }

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
                    Text(
                        modifier = Modifier.padding(top = 10.dp),
                        fontSize = 25.sp,
                        text = updatedContext.resources.getString(R.string.we_wish_you_a_pleasant_stay),
                        fontFamily = FontFamily(Font(R.font.nokora_light)),
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )

                    Row(
                        modifier = Modifier.padding(top = 10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.english_language),
                            contentDescription = stringResource(R.string.english_language),
                            modifier = Modifier
                                .padding(end = 10.dp)
                                .clickable {
                                    updatedContext = setLocale(Locale.ENGLISH, updatedContext)
                                }
                                .size(50.dp)
                        )

                        Image(
                            painter = painterResource(id = R.drawable.georgian_language),
                            contentDescription = stringResource(R.string.georgian_language),
                            modifier = Modifier
                                .clickable {
                                    updatedContext = setLocale(Locale("ka"), updatedContext)
                                }
                                .size(50.dp)
                        )
                    }
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
                        text = stringResource(R.string.date),
                        fontFamily = FontFamily(Font(R.font.nokora_light)),
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }

            CategoriesRow(updatedContext)
        }
    }
}