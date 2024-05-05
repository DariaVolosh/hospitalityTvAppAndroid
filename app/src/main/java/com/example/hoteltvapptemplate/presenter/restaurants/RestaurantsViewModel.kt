package com.example.hoteltvapptemplate.presenter.restaurants

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.hoteltvapptemplate.mappers.RestaurantDataToPhotoMapper
import javax.inject.Inject

class RestaurantsViewModel @Inject constructor(
    private val restaurantDataToPhotoMapper: RestaurantDataToPhotoMapper
): ViewModel() {
    fun getPhotos() = restaurantDataToPhotoMapper.getPhotos()
    fun getRestaurantsInformation() = restaurantDataToPhotoMapper.getRestaurantsInformation()
    fun setMapperContext(context: Context) = restaurantDataToPhotoMapper.setContext(context)
}