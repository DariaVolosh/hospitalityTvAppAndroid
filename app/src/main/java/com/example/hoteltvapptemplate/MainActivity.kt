package com.example.hoteltvapptemplate

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hoteltvapptemplate.presenter.categories.CategoriesViewModel
import com.example.hoteltvapptemplate.presenter.hotelInfo.HotelInfoScreen
import com.example.hoteltvapptemplate.presenter.hotelInfo.HotelInfoViewModel
import com.example.hoteltvapptemplate.presenter.screen.ScreenViewModel
import com.example.hoteltvapptemplate.presenter.services.ServicesScreen
import com.example.hoteltvapptemplate.presenter.services.ServicesViewModel
import com.example.hoteltvapptemplate.presenter.weather.WeatherScreen
import com.example.hoteltvapptemplate.presenter.weather.WeatherViewModel
import com.example.hoteltvapptemplate.presenter.welcome.WelcomeScreen
import com.example.hoteltvapptemplate.ui.theme.TvAppTheme
import javax.inject.Inject

data class MainScreenViewModels @Inject constructor(
    val screenViewModel: ScreenViewModel,
    val categoriesViewModel: CategoriesViewModel,
    val servicesViewModel: ServicesViewModel,
    val hotelInfoViewModel: HotelInfoViewModel,
    val weatherViewModel: WeatherViewModel
)

class MainActivity : AppCompatActivity() {
    @Inject lateinit var mainScreenViewModels: MainScreenViewModels

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val application = application as MyApplication

        application.appComponent.inject(this)
        setContent {
            TvAppTheme {
                MainScreen(
                    mainScreenViewModels,
                    { context -> application.updateContext(context)}
                ) { application.getContext() }
            }
        }
    }
}


fun navigateToCategory(
    screenName: String,
    navController: NavController,
) {
    navController.navigate(screenName)
}

@Composable
fun MainScreen(
    mainScreenViewModels: MainScreenViewModels,
    updateContext: (Context) -> Unit,
    getContext: () -> Context
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "welcomeScreen"
    ) {
        composable("welcomeScreen") {
            WelcomeScreen(
                mainScreenViewModels.categoriesViewModel,
                mainScreenViewModels.screenViewModel,
                { navigateToCategory(it, navController) }
            ) { updateContext(it) }
        }

        composable("servicesScreen") {
            ServicesScreen(
                mainScreenViewModels.screenViewModel,
                mainScreenViewModels.categoriesViewModel,
                mainScreenViewModels.servicesViewModel,
                { navigateToCategory(it, navController) },
                getContext
            )
        }

        composable("hotelInfoScreen") {
            HotelInfoScreen(
                mainScreenViewModels.hotelInfoViewModel,
                mainScreenViewModels.screenViewModel,
                mainScreenViewModels.categoriesViewModel,
                { navigateToCategory(it, navController) },
                getContext
            )
        }

        composable("weatherScreen") {
            WeatherScreen(
                mainScreenViewModels.weatherViewModel,
                mainScreenViewModels.categoriesViewModel,
                mainScreenViewModels.screenViewModel,
                { navigateToCategory(it, navController) },
                getContext
            )
        }
    }
}