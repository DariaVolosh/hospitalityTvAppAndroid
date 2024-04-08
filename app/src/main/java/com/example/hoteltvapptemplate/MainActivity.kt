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
import com.example.hoteltvapptemplate.presenter.restaurants.RestaurantsScreen
import com.example.hoteltvapptemplate.presenter.restaurants.RestaurantsViewModel
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
    val weatherViewModel: WeatherViewModel,
    val restaurantsViewModel: RestaurantsViewModel
)

class DefaultParameters @Inject constructor() {
    private lateinit var navController: NavController
    private lateinit var application: MyApplication
    fun navigateToCategory(screenName: String) {
        navController.navigate(screenName)
    }
    fun setApplication(app: MyApplication) {
        application = app
    }
    fun setNavController(controller: NavController) {
        navController = controller
    }
    fun getContext(): Context {
        return application.getContext()
    }
    fun updateContext(context: Context) = application.updateContext(context)
}

data class ScreenParameters @Inject constructor(
    val mainScreenViewModels: MainScreenViewModels,
    val defaultParameters: DefaultParameters
)

class MainActivity : AppCompatActivity() {
    @Inject lateinit var screenParameters: ScreenParameters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val application = application as MyApplication
        application.appComponent.inject(this)
        screenParameters.defaultParameters.setApplication(application)
        setContent {
            TvAppTheme {
                MainScreen(screenParameters)
            }
        }
    }
}

@Composable
fun MainScreen(
    screenParameters: ScreenParameters
) {
    val navController = rememberNavController()
    screenParameters.defaultParameters.setNavController(navController)

    NavHost(
        navController = navController,
        startDestination = "welcomeScreen"
    ) {
        composable("welcomeScreen") {
            WelcomeScreen(screenParameters)
        }

        composable("servicesScreen") {
            ServicesScreen(screenParameters)
        }

        composable("hotelInfoScreen") {
            HotelInfoScreen(screenParameters)
        }

        composable("weatherScreen") {
            WeatherScreen(screenParameters)
        }

        composable("restaurantsScreen") {
            RestaurantsScreen(screenParameters)
        }
    }
}