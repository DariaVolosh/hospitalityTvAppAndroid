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
            getString(R.string.hotel_services) to
                listOf(
                    getString(R.string.front_desk_service),
                    getString(R.string.room_service),
                    getString(R.string.airport_shuttle),
                    getString(R.string.banquet_facilities),
                    getString(R.string.bar),
                    getString(R.string.buffet_breakfast),
                    getString(R.string.bus_truck_parking),
                    getString(R.string.business_center)
                ),
            getString(R.string.accessible_amenities)to
                    listOf(
                        getString(R.string.public_entrance),
                        getString(R.string.route_to_accessible_guestrooms),
                        getString(R.string.route_to_accessible_parking),
                        getString(R.string.van_parking),
                        getString(R.string.service_to_guests_with_disabilities),
                        getString(R.string.wheelchair_accessible_elevators)
                    ),
        )

        servicesMapper = newMapper
    }

    fun getServices() = servicesMapper.keys
    fun getSpecificServices() = servicesMapper.values
}