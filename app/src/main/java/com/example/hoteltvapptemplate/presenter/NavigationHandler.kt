package com.example.hoteltvapptemplate.presenter

import androidx.navigation.NavController
import javax.inject.Inject

class NavigationHandler @Inject constructor() {
    private lateinit var navController: NavController

    fun navigateToCategory(screenName: String) {
        navController.navigate(screenName)
    }

    fun setNavController(controller: NavController) {
        navController = controller
    }
}