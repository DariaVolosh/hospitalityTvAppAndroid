package com.example.hoteltvapptemplate.presenter.mappers

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

            getString(R.string.our_story) to
                getString(R.string.our_story_text),

            getString(R.string.luxury) to
                getString(R.string.luxury_text),

            getString(R.string.dining) to
                getString(R.string.dining_text),

            getString(R.string.events) to
                getString(R.string.events_text)
        )

        infoHeaderToDescriptionMapper = newMapper
    }

    fun getHeaders() = infoHeaderToDescriptionMapper.keys
    fun getDescriptions() = infoHeaderToDescriptionMapper.values
}