package com.example.hoteltvapptemplate.presenter.services

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.hoteltvapptemplate.mappers.ServicesMapper
import javax.inject.Inject

class ServicesViewModel @Inject constructor(
    private val servicesMapper: ServicesMapper
): ViewModel() {
    fun getServices() = servicesMapper.getServices()
    fun getSpecificServices() = servicesMapper.getSpecificServices()
    fun setMapperContext(context: Context) = servicesMapper.setContext(context)
}