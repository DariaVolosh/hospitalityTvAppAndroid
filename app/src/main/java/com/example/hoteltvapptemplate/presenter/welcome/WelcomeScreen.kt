package com.example.hoteltvapptemplate.presenter.welcome

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hoteltvapptemplate.R
import com.example.hoteltvapptemplate.presenter.categories.CategoriesViewModel
import com.example.hoteltvapptemplate.presenter.screen.ScreenBackground
import com.example.hoteltvapptemplate.presenter.screen.ScreenViewModel
import java.util.Locale

fun setLocale(locale: Locale, oldContext: Context): Context {
    val config = Configuration(oldContext.resources.configuration)
    config.setLocale(locale)
    return oldContext.createConfigurationContext(config)
}

@Composable
fun WelcomeScreen(
    categoriesViewModel: CategoriesViewModel,
    screenViewModel: ScreenViewModel,
    navigateToCategory: (String) -> Unit,
    updateContext: (Context) -> Unit
) {
    val curr = LocalContext.current.applicationContext
    var updatedContext by remember { mutableStateOf(curr) }
    
    DisposableEffect(Unit) {
        onDispose {
            screenViewModel.isWelcomeScreen.value = false
        }
    }

    ScreenBackground(
        screenViewModel,
        categoriesViewModel,
        updatedContext.resources.getString(R.string.welcome_to_our_hotel),
        navigateToCategory,
        updatedContext,
        {}
    ) {
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
                        val newContext = setLocale(Locale.ENGLISH, updatedContext)
                        updateContext(newContext)
                        updatedContext = newContext
                    }
                    .size(50.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.georgian_language),
                contentDescription = stringResource(R.string.georgian_language),
                modifier = Modifier
                    .clickable {
                        val newContext = setLocale(Locale("ka"), updatedContext)
                        updateContext(newContext)
                        updatedContext = newContext
                    }
                    .size(50.dp)
            )
        }
    }
}