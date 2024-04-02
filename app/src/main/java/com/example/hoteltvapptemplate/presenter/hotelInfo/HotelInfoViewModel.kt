package com.example.hoteltvapptemplate.presenter.hotelInfo

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.hoteltvapptemplate.presenter.mappers.HotelInfoMapper
import javax.inject.Inject

class HotelInfoViewModel @Inject constructor(
    private val hotelInfoMapper: HotelInfoMapper
): ViewModel() {
    fun getHeaders() = hotelInfoMapper.getHeaders()

    fun getDescriptions() = hotelInfoMapper.getDescriptions()

    fun setMapperContext(context: Context) = hotelInfoMapper.setContext(context)
}