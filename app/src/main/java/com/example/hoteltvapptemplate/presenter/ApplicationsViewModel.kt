package com.example.hoteltvapptemplate.presenter

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.hoteltvapptemplate.useCases.ApplicationsUseCase
import javax.inject.Inject

class ApplicationsViewModel @Inject constructor(
    private val applicationsUseCase: ApplicationsUseCase
): ViewModel() {
    fun getContext() = applicationsUseCase.getContext()

    fun updateContext(context: Context) {
        applicationsUseCase.updateContext(context)
    }

    fun launchExternalApp(categoryName: String) {
        applicationsUseCase.launchExternalApp(categoryName)
    }
}