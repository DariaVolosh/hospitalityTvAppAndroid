package com.example.hoteltvapptemplate.useCases

import com.example.hoteltvapptemplate.data.repositories.AmazonDynamoDB
import javax.inject.Inject

class GetAvailableAppVersionUseCase @Inject constructor(
    private val amazonDynamoDB: AmazonDynamoDB
) {
    suspend fun getAvailableAppVersion() =
        amazonDynamoDB.getAvailableAppVersion()
}