package com.example.hoteltvapptemplate.useCases

import android.content.Context
import com.example.hoteltvapptemplate.data.repositories.ApplicationHandler
import com.example.hoteltvapptemplate.data.repositories.ExternalAppLauncher
import javax.inject.Inject

class ApplicationsUseCase @Inject constructor(
    private val applicationHandler: ApplicationHandler,
    private val externalAppLauncher: ExternalAppLauncher
) {
    fun getContext() = applicationHandler.getContext()

    fun updateContext(context: Context) = applicationHandler.updateContext(context)

    fun launchExternalApp(categoryName: String) {
        externalAppLauncher.launchExternalApp(categoryName, getContext())
    }
}