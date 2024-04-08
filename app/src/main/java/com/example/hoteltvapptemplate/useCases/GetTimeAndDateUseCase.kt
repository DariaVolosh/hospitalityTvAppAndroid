package com.example.hoteltvapptemplate.useCases

import com.example.hoteltvapptemplate.data.repositories.TimeAndDateRepository
import javax.inject.Inject

class GetTimeAndDateUseCase @Inject constructor(
    private val timeAndDateRepository: TimeAndDateRepository
) {
    fun getTimeAndDateFlow() = timeAndDateRepository.getTimeAndDateFlow()
}