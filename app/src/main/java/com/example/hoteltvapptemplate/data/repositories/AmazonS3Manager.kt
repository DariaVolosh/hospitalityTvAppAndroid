package com.example.hoteltvapptemplate.data.repositories

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.GetObjectRequest
import com.example.hoteltvapptemplate.data.APK_OBJECT_NAME
import com.example.hoteltvapptemplate.data.APPLICATION_BUCKET_NAME
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import javax.inject.Inject

class AmazonS3Manager @Inject constructor(
    private val s3Client: AmazonS3Client
) {
    suspend fun getUpdatedApplicationInputStream(): InputStream =
        withContext(Dispatchers.IO) {
            val s3object = s3Client.getObject(
                GetObjectRequest(
                    APPLICATION_BUCKET_NAME,
                    APK_OBJECT_NAME
                )
            )

            s3object.objectContent
        }
}