package com.example.hoteltvapptemplate.presenter.mappers

import android.content.Context
import com.example.hoteltvapptemplate.R
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CategoriesMapper @Inject constructor(
    override var appContext: Context
) : Mapper {
    private var categoryNamesMapper = mapOf<String, Pair<Int,String>>()

    override fun updateNamesMapper() {
        // mapper from category name to corresponding icon and composable screen name for navigation
        val newMapper = mapOf(
            appContext.resources.getString(R.string.tv_channels) to Pair(
                R.drawable.tv_channels,
                appContext.getString(R.string.tv_channels_screen)
            ),
            appContext.resources.getString(R.string.our_services) to Pair(
                R.drawable.our_services,
                appContext.getString(R.string.services_screen)
            ),
            appContext.resources.getString(R.string.weather) to Pair(
                R.drawable.weather,
                appContext.getString(R.string.weather_screen)
            ),
            appContext.resources.getString(R.string.hotel_info) to Pair(
                R.drawable.hotel_info,
                appContext.getString(R.string.hotel_info_screen)
            ),
            appContext.resources.getString(R.string.restaurants) to Pair(
                R.drawable.restaurants,
                appContext.getString(R.string.restaurants_screen)
            ),
            appContext.resources.getString(R.string.rooms) to Pair(
                R.drawable.rooms,
                appContext.getString(R.string.rooms_screen)
            )
        )
        categoryNamesMapper = newMapper
    }

    fun mapScreenName(categoryName: String) = categoryNamesMapper[categoryName]?.second ?: ""
    fun mapIcon(categoryName: String) = categoryNamesMapper[categoryName]?.first ?: 0
    fun getCategoriesNames() = categoryNamesMapper.keys
}