package com.example.hoteltvapptemplate.mappers

import android.content.Context
import com.example.hoteltvapptemplate.R
import javax.inject.Inject

class HotelInfoMapper @Inject constructor(
   override var appContext: Context
): Mapper {

    private var infoHeaderToDescriptionMapper = mapOf<String, String>()

    override fun updateNamesMapper() {
        val newMapper = mapOf(
            getString(R.string.welcome_to_hotel) to
                getString(R.string.welcome_to_hotel_text),

            getString(R.string.our_top_destinations) to
                getString(R.string.our_top_destinations_text),

            getString(R.string.say_hello_to_red) to
                getString(R.string.say_hello_to_red_text),

            getString(R.string.room_and_amenities) to
                getString(R.string.room_and_amenities_text),

            getString(R.string.dining) to
                getString(R.string.dining_text)
        )

        infoHeaderToDescriptionMapper = newMapper
    }

    fun getHeaders() = infoHeaderToDescriptionMapper.keys
    fun getDescriptions() = infoHeaderToDescriptionMapper.values
}