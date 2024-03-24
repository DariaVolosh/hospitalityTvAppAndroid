package com.example.hoteltvapptemplate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hoteltvapptemplate.ui.theme.TvAppTheme
import com.example.hoteltvapptemplate.presenter.WelcomeScreen
import com.example.hoteltvapptemplate.presenter.WelcomeScreenViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject lateinit var welcomeScreenViewModel: WelcomeScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as MyApplication).appComponent.inject(this)
        setContent {
            TvAppTheme {
                MainScreen(welcomeScreenViewModel)
            }
        }
    }
}

@Composable
fun MainScreen(
    welcomeScreenViewModel: WelcomeScreenViewModel
) {
    NavHost(
        navController = rememberNavController(),
        startDestination = "welcomeScreen"
    ) {
        composable("welcomeScreen") {
            WelcomeScreen(welcomeScreenViewModel)
        }
    }
}