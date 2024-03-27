package com.example.hoteltvapptemplate.presenter.mappers

import android.content.Context
import com.example.hoteltvapptemplate.R
import javax.inject.Inject

class CategoriesMapper @Inject constructor(
    private var context: Context
) {
    private var categoryNamesMapper = mapOf<String, Pair<Int,String>>()

    private fun updateNamesMapper() {

        // mapper from category name to corresponding icon and composable screen name for navigation
        val newMapper = mapOf(
            context.resources.getString(R.string.tv_channels) to Pair(
                R.drawable.tv_channels,
                context.getString(R.string.tv_channels_screen)
            ),
            context.resources.getString(R.string.our_services) to Pair(
                R.drawable.our_services,
                context.getString(R.string.services_screen)
            ),
            context.resources.getString(R.string.weather) to Pair(
                R.drawable.weather,
                context.getString(R.string.weather_screen)
            ),
            context.resources.getString(R.string.hotel_info) to Pair(
                R.drawable.hotel_info,
                context.getString(R.string.hotel_info_screen)
            ),
            context.resources.getString(R.string.restaurants) to Pair(
                R.drawable.restaurants,
                context.getString(R.string.restaurants_screen)
            ),
            context.resources.getString(R.string.rooms) to Pair(
                R.drawable.rooms,
                context.getString(R.string.rooms_screen)
            )
        )
        categoryNamesMapper = newMapper
    }

    fun mapScreenName(categoryName: String) = categoryNamesMapper[categoryName]?.second ?: ""
    fun mapIcon(categoryName: String) = categoryNamesMapper[categoryName]?.first ?: 0
    fun getCategoriesNames() = categoryNamesMapper.keys
    fun setContext(newContext: Context) {
        context = newContext
        updateNamesMapper()
    }
}