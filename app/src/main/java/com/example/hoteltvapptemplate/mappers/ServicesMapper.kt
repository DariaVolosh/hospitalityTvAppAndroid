package com.example.hoteltvapptemplate.mappers

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
            getString(R.string.accommodation_services) to
                listOf(
                    getString(R.string.room_booking),
                    getString(R.string.check_in),
                    getString(R.string.room_cleaning),
                    getString(R.string.laundry)
                ),
            getString(R.string.dining_services )to
                    listOf(
                        getString(R.string.restaurants_and_cafes),
                        getString(R.string.room_service),
                        getString(R.string.breakfast),
                        getString(R.string.catering)
                    ),
            getString(R.string.recreational_services) to
                    listOf(
                        getString(R.string.swimming),
                        getString(R.string.fitness),
                        getString(R.string.spa),
                        getString(R.string.sauna)
                    ),
        )

        servicesMapper = newMapper
    }

    fun getServices() = servicesMapper.keys
    fun getSpecificServices() = servicesMapper.values
}