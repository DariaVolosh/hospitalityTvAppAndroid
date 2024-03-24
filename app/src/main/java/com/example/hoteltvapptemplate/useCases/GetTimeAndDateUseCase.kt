package com.example.hoteltvapptemplate.useCases

import com.example.hoteltvapptemplate.data.TimeAndDateRepository
import javax.inject.Inject

class GetTimeAndDateUseCase @Inject constructor(
    private val timeAndDateRepository: TimeAndDateRepository
) {
    fun getTimeAndDateFlow() = timeAndDateRepository.getTimeAndDateFlow()
}