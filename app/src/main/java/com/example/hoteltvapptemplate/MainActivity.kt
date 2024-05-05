package com.example.hoteltvapptemplate

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hoteltvapptemplate.presenter.ApplicationsViewModel
import com.example.hoteltvapptemplate.presenter.NavigationHandler
import com.example.hoteltvapptemplate.presenter.categories.CategoriesViewModel
import com.example.hoteltvapptemplate.presenter.hotelInfo.HotelInfoScreen
import com.example.hoteltvapptemplate.presenter.hotelInfo.HotelInfoViewModel
import com.example.hoteltvapptemplate.presenter.restaurants.RestaurantsScreen
import com.example.hoteltvapptemplate.presenter.restaurants.RestaurantsViewModel
import com.example.hoteltvapptemplate.presenter.rooms.RoomsScreen
import com.example.hoteltvapptemplate.presenter.rooms.RoomsViewModel
import com.example.hoteltvapptemplate.presenter.screen.ScreenViewModel
import com.example.hoteltvapptemplate.presenter.services.ServicesScreen
import com.example.hoteltvapptemplate.presenter.services.ServicesViewModel
import com.example.hoteltvapptemplate.presenter.welcome.WelcomeScreen
import com.example.hoteltvapptemplate.presenter.welcome.WelcomeViewModel
import com.example.hoteltvapptemplate.ui.theme.TvAppTheme
import dagger.Lazy
import javax.inject.Inject

data class MainScreenViewModels @Inject constructor(
    val welcomeViewModel: Lazy<WelcomeViewModel>,
    val screenViewModel: Lazy<ScreenViewModel>,
    val categoriesViewModel: Lazy<CategoriesViewModel>,
    val servicesViewModel: Lazy<ServicesViewModel>,
    val hotelInfoViewModel: Lazy<HotelInfoViewModel>,
    val restaurantsViewModel: Lazy<RestaurantsViewModel>,
    val roomsViewModel: Lazy<RoomsViewModel>,
    val applicationsViewModel: Lazy<ApplicationsViewModel>
)

data class ScreenParameters @Inject constructor(
    val mainScreenViewModels: MainScreenViewModels,
    val navigationHandler: NavigationHandler
)

class MainActivity : AppCompatActivity() {
    @Inject lateinit var screenParameters: ScreenParameters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val application = application as MyApplication
        application.appComponent.passAppAndContext(applicationContext, application)
            .inject(this)

        setContent {
            TvAppTheme {
                MainScreen(screenParameters)
            }
        }
    }
}

@Composable
fun MainScreen(screenParameters: ScreenParameters) {
    val navController = rememberNavController()
    screenParameters.navigationHandler.setNavController(navController)

    NavHost(
        navController = navController,
        startDestination = stringResource(R.string.welcome_screen)
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

        composable("restaurantsScreen") {
            RestaurantsScreen(screenParameters)
        }

        composable("roomsScreen") {
            RoomsScreen(screenParameters)
        }
    }
}