package com.example.hoteltvapptemplate.presenter.welcome

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hoteltvapptemplate.data.APK_OBJECT_NAME
import com.example.hoteltvapptemplate.useCases.ApplicationsUseCase
import com.example.hoteltvapptemplate.useCases.GetUpdatedApplicationInputStreamUseCase
import com.example.hoteltvapptemplate.useCases.SaveFileFromInputStreamUseCase
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

class WelcomeViewModel @Inject constructor(
    private val getUpdatedApplicationInputStreamUseCase: GetUpdatedApplicationInputStreamUseCase,
    private val saveFileFromInputStreamUseCase: SaveFileFromInputStreamUseCase,
    private val applicationsUseCase: ApplicationsUseCase
): ViewModel() {

    fun downloadAndInstallApk(context: Context, filesDir: File) {
        viewModelScope.launch {
            val file = File(filesDir, APK_OBJECT_NAME)
            file.delete()
            val inputStream = getUpdatedApplicationInputStreamUseCase.getUpdatedApplicationInputStream()
            saveFileFromInputStreamUseCase.saveFileFromInputStream(inputStream, file)
            applicationsUseCase.installApk(context, file)
        }
    }
}