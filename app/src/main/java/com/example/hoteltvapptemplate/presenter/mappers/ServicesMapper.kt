package com.example.hoteltvapptemplate.presenter.mappers

import android.content.Context
import com.example.hoteltvapptemplate.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServicesMapper @Inject constructor(
    override var appContext: Context
): Mapper {
    private var servicesMapper = mapOf<String, List<String>>()

    override fun updateNamesMapper() {
        // mapper from service name to a list of specific related services list
        val newMapper = mapOf(
            appContext.resources.getString(R.string.accommodation_services) to
                listOf(
                    appContext.resources.getString(R.string.room_booking),
                    appContext.resources.getString(R.string.check_in),
                    appContext.resources.getString(R.string.room_cleaning),
                    appContext.resources.getString(R.string.laundry)
                ),
            appContext.resources.getString(R.string.dining_services) to
                    listOf(
                        appContext.resources.getString(R.string.restaurants_and_cafes),
                        appContext.resources.getString(R.string.room_service),
                        appContext.resources.getString(R.string.breakfast),
                        appContext.resources.getString(R.string.catering)
                    ),
            appContext.resources.getString(R.string.recreational_services) to
                    listOf(
                        appContext.resources.getString(R.string.swimming),
                        appContext.resources.getString(R.string.fitness),
                        appContext.resources.getString(R.string.spa),
                        appContext.resources.getString(R.string.sauna)
                    ),
        )

        servicesMapper = newMapper
    }

    fun getServices() = servicesMapper.keys
    fun getSpecificServices() = servicesMapper.values
}