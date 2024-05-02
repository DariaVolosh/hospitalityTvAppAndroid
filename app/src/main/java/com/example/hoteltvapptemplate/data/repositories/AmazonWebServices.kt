package com.example.hoteltvapptemplate.data.repositories

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient
import com.amazonaws.services.dynamodbv2.model.AttributeValue
import com.amazonaws.services.dynamodbv2.model.GetItemRequest
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.GetObjectRequest
import com.example.hoteltvapptemplate.data.APK_OBJECT_NAME
import com.example.hoteltvapptemplate.data.DYNAMO_DB_TABLE_NAME
import com.example.hoteltvapptemplate.data.PRIMARY_KEY_NAME
import com.example.hoteltvapptemplate.data.PRIMARY_KEY_VALUE
import com.example.hoteltvapptemplate.data.S3_NAME
import com.example.hoteltvapptemplate.data.VERSION_KEY_NAME
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import javax.inject.Inject

interface AmazonWebServices

class AmazonS3Manager @Inject constructor(
    private val s3Client: AmazonS3Client
): AmazonWebServices {
    suspend fun getUpdatedApplicationInputStream(): InputStream =
        withContext(Dispatchers.IO) {
            val s3object = s3Client.getObject(
                GetObjectRequest(
                    S3_NAME,
                    APK_OBJECT_NAME
                )
            )

            s3object.objectContent
        }
}

class AmazonDynamoDB @Inject constructor(
    private val dynamoDBClient: AmazonDynamoDBClient
): AmazonWebServices {
    suspend fun getAvailableAppVersion() =
        withContext(Dispatchers.IO) {
            dynamoDBClient.getItem(
                GetItemRequest(
                    DYNAMO_DB_TABLE_NAME,
                    mapOf(PRIMARY_KEY_NAME to AttributeValue(PRIMARY_KEY_VALUE))
                ).withProjectionExpression(VERSION_KEY_NAME)
            ).item[VERSION_KEY_NAME]?.s ?: ""
        }
}