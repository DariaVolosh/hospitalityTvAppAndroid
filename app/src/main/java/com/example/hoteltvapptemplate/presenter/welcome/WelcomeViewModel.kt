package com.example.hoteltvapptemplate.presenter.welcome

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hoteltvapptemplate.useCases.ApplicationsUseCase
import com.example.hoteltvapptemplate.useCases.GetAvailableAppVersionUseCase
import com.example.hoteltvapptemplate.useCases.GetUpdatedApplicationInputStreamUseCase
import com.example.hoteltvapptemplate.useCases.SaveFileFromInputStreamUseCase
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

class WelcomeViewModel @Inject constructor(
    private val getUpdatedApplicationInputStreamUseCase: GetUpdatedApplicationInputStreamUseCase,
    private val saveFileFromInputStreamUseCase: SaveFileFromInputStreamUseCase,
    private val applicationsUseCase: ApplicationsUseCase,
    private val getAvailableAppVersionUseCase: GetAvailableAppVersionUseCase
): ViewModel() {
    val availableVersion = MutableLiveData<String>()

    init {
        getAvailableAppVersion()
    }

    fun downloadAndInstallApk(context: Context, filesDir: File) {
        viewModelScope.launch {
            // solve the issue with app downloading
            /* val file = File(filesDir, APK_OBJECT_NAME)
            file.delete()
            val inputStream = getUpdatedApplicationInputStreamUseCase.getUpdatedApplicationInputStream()
            saveFileFromInputStreamUseCase.saveFileFromInputStream(inputStream, file)
            applicationsUseCase.installApk(context, file) */
        }
    }

    private fun getAvailableAppVersion() {
        viewModelScope.launch {
            availableVersion.value = getAvailableAppVersionUseCase.getAvailableAppVersion()
        }
    }
}