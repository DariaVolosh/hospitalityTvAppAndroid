package com.example.hoteltvapptemplate.useCases

import com.example.hoteltvapptemplate.data.repositories.AmazonS3Manager
import javax.inject.Inject

class GetUpdatedApplicationInputStreamUseCase @Inject constructor(
    private val amazonS3Manager: AmazonS3Manager
) {
    suspend fun getUpdatedApplicationInputStream() =
        amazonS3Manager.getUpdatedApplicationInputStream()
}