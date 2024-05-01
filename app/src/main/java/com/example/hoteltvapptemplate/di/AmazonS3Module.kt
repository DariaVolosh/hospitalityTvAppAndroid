package com.example.hoteltvapptemplate.di

import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Region
import com.amazonaws.services.s3.AmazonS3Client
import com.example.hoteltvapptemplate.data.AMAZON_ACCESS_KEY
import com.example.hoteltvapptemplate.data.AMAZON_REGION
import com.example.hoteltvapptemplate.data.AMAZON_SECRET_KEY
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AmazonS3Module {
    @Provides
    @Singleton
    fun provideAmazonS3Client() =
        AmazonS3Client(
            BasicAWSCredentials(AMAZON_ACCESS_KEY, AMAZON_SECRET_KEY),
            Region.getRegion(AMAZON_REGION)
        )
}