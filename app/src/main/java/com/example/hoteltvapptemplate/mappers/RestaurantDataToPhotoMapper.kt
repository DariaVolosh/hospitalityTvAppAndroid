package com.example.hoteltvapptemplate.mappers

import android.content.Context
import com.example.hoteltvapptemplate.R
import com.example.hoteltvapptemplate.presenter.restaurants.Restaurant
import javax.inject.Inject

class RestaurantDataToPhotoMapper @Inject constructor(
    override var appContext: Context
): Mapper {
    private var restaurantToPhotoMapper = mapOf<Int, Restaurant>()

    override fun updateNamesMapper() {
        val newMapper = mapOf(
            R.drawable.gandagana_restaurant to
                    Restaurant(
                        getString(R.string.restaurant_2_name),
                        getString(R.string.restaurant_2_description),
                        getString(R.string.restaurant_2_open_hours)
                    ),

            R.drawable.gandagana_restaurant to
                    Restaurant(
                        getString(R.string.restaurant_3_name),
                        getString(R.string.restaurant_3_description),
                        getString(R.string.restaurant_3_open_hours)
                    ),

            R.drawable.gandagana_restaurant to
                    Restaurant(
                        getString(R.string.restaurant_1_name),
                        getString(R.string.restaurant_1_description),
                        getString(R.string.restaurant_1_open_hours)
                    ),
        )

        restaurantToPhotoMapper = newMapper
    }

    fun getPhotos() = restaurantToPhotoMapper.keys
    fun getRestaurantsInformation() = restaurantToPhotoMapper.values
}