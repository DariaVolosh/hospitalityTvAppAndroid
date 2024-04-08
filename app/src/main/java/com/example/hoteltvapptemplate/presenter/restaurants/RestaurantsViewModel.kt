package com.example.hoteltvapptemplate.presenter.restaurants

import android.content.Context
import com.example.hoteltvapptemplate.R
import javax.inject.Inject

class RestaurantsViewModel @Inject constructor(
    private val appContext: Context
) {
    private val restaurantsList: List<Restaurant> = loadRestaurantsData()

    private fun loadRestaurantsData(): List<Restaurant> {
        return listOf(
            Restaurant(
                appContext.getString(R.string.restaurant_1_name),
                appContext.getString(R.string.restaurant_1_description),
                appContext.getString(R.string.restaurant_1_open_hours)
            ),
            Restaurant(
                appContext.getString(R.string.restaurant_2_name),
                appContext.getString(R.string.restaurant_2_description),
                appContext.getString(R.string.restaurant_2_open_hours)
            ),
            Restaurant(
                appContext.getString(R.string.restaurant_3_name),
                appContext.getString(R.string.restaurant_3_description),
                appContext.getString(R.string.restaurant_3_open_hours)
            ),
            Restaurant(
                appContext.getString(R.string.restaurant_4_name),
                appContext.getString(R.string.restaurant_4_description),
                appContext.getString(R.string.restaurant_4_open_hours)
            )
        )
    }

    fun getRestaurantsData() = restaurantsList
}