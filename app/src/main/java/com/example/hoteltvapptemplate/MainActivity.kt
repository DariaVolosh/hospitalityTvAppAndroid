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
import com.example.hoteltvapptemplate.presenter.welcome.WelcomeScreen
import com.example.hoteltvapptemplate.ui.theme.TvAppTheme
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject lateinit var screenViewModel: ScreenViewModel
    @Inject lateinit var categoriesViewModel: CategoriesViewModel
    @Inject lateinit var servicesViewModel: ServicesViewModel
    @Inject lateinit var hotelInfoViewModel: HotelInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val application = application as MyApplication

        application.appComponent.inject(this)
        setContent {
            TvAppTheme {
                MainScreen(
                    screenViewModel,
                    categoriesViewModel,
                    servicesViewModel,
                    hotelInfoViewModel,
                    { context -> application.updateContext(context)}) {
                    application.getContext()
                }
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
    screenViewModel: ScreenViewModel,
    categoriesViewModel: CategoriesViewModel,
    servicesViewModel: ServicesViewModel,
    hotelInfoViewModel: HotelInfoViewModel,
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
                categoriesViewModel,
                screenViewModel,
                { navigateToCategory(it, navController) }
            ) { updateContext(it) }
        }

        composable("servicesScreen") {
            ServicesScreen(
                screenViewModel,
                categoriesViewModel,
                servicesViewModel,
                { navigateToCategory(it, navController) },
                getContext
            )
        }

        composable("hotelInfoScreen") {
            HotelInfoScreen(
                hotelInfoViewModel,
                screenViewModel,
                categoriesViewModel,
                { navigateToCategory(it, navController) },
                getContext
            )
        }
    }
}