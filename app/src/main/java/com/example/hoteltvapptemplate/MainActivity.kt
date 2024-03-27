package com.example.hoteltvapptemplate

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hoteltvapptemplate.ui.theme.TvAppTheme
import com.example.hoteltvapptemplate.presenter.welcome.WelcomeScreen
import com.example.hoteltvapptemplate.presenter.ScreenViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject lateinit var screenViewModel: ScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val application = application as MyApplication

        application.appComponent.inject(this)
        setContent {
            TvAppTheme {
                MainScreen(screenViewModel) { context ->
                    application.updateContext(context)
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    screenViewModel: ScreenViewModel,
    updateContext: (Context) -> Unit
) {
    NavHost(
        navController = rememberNavController(),
        startDestination = "welcomeScreen"
    ) {
        composable("welcomeScreen") {
            WelcomeScreen(screenViewModel, updateContext)
        }
    }
}