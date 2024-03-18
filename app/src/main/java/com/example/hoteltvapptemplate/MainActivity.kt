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

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TvAppTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    NavHost(
        navController = rememberNavController(),
        startDestination = "welcomeScreen"
    ) {
        composable("welcomeScreen") {
            WelcomeScreen()
        }
    }
}