package com.example.hoteltvapptemplate.mappers

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
            getString(R.string.youtube) to Pair(
                R.drawable.youtube,
                getString(R.string.youtube_screen)
            ),
            getString(R.string.tv_channels) to Pair(
                R.drawable.tv_channels,
                getString(R.string.tv_channels_screen)
            ),
            getString(R.string.our_services) to Pair(
                R.drawable.our_services,
                getString(R.string.services_screen)
            ),
            getString(R.string.weather) to Pair(
                R.drawable.weather,
                getString(R.string.weather_screen)
            ),
            getString(R.string.hotel_info) to Pair(
                R.drawable.hotel_info,
                getString(R.string.hotel_info_screen)
            ),
            getString(R.string.restaurants) to Pair(
                R.drawable.restaurants,
                getString(R.string.restaurants_screen)
            ),
            getString(R.string.rooms) to Pair(
                R.drawable.rooms,
                getString(R.string.rooms_screen)
            )
        )
        categoryNamesMapper = newMapper
    }

    fun mapScreenName(categoryName: String) = categoryNamesMapper[categoryName]?.second ?: ""
    fun mapIcon(categoryName: String) = categoryNamesMapper[categoryName]?.first ?: 0
    fun getCategoriesNames() = categoryNamesMapper.keys
}