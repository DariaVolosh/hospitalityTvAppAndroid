package com.example.hoteltvapptemplate.presenter.applications

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.hoteltvapptemplate.R
import com.example.hoteltvapptemplate.useCases.ApplicationsUseCase
import javax.inject.Inject

class ApplicationsViewModel @Inject constructor(
    private val applicationsUseCase: ApplicationsUseCase
): ViewModel() {
    val applicationsList: List<ExternalApplicationInfo> = listOf(
        ExternalApplicationInfo(
            R.drawable.youtube,
            R.string.youtube
        ),

        ExternalApplicationInfo(
            R.drawable.spotify,
            R.string.spotify
        ),

        ExternalApplicationInfo(
            R.drawable.netflix,
            R.string.netflix
        ),

        ExternalApplicationInfo(
            R.drawable.setanta,
            R.string.setanta
        )
    )

    fun getContext() = applicationsUseCase.getContext()

    fun updateContext(context: Context) {
        applicationsUseCase.updateContext(context)
    }

    fun launchExternalApp(categoryName: String) {
        applicationsUseCase.launchExternalApp(categoryName)
    }
}