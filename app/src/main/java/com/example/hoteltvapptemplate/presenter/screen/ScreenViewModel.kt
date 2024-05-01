package com.example.hoteltvapptemplate.presenter.screen

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hoteltvapptemplate.useCases.ApplicationsUseCase
import com.example.hoteltvapptemplate.useCases.GetTimeAndDateUseCase
import com.example.hoteltvapptemplate.useCases.GetUpdatedApplicationInputStreamUseCase
import com.example.hoteltvapptemplate.useCases.SaveFileFromInputStreamUseCase
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScreenViewModel @Inject constructor(
    private val getTimeAndDateUseCase: GetTimeAndDateUseCase,
    private val getUpdatedApplicationInputStreamUseCase: GetUpdatedApplicationInputStreamUseCase,
    private val saveFileFromInputStreamUseCase: SaveFileFromInputStreamUseCase,
    private val applicationsUseCase: ApplicationsUseCase
): ViewModel() {
    val time = MutableLiveData("")
    val date = MutableLiveData("")

    // isWelcomeScreen variable used to detect if categories row should be expanded by default
    // (which is only on welcome screen) and on other screens it should not be expanded
    var isWelcomeScreen = MutableLiveData(true)

    init {
        viewModelScope.launch {
            getTimeAndDateUseCase.getTimeAndDateFlow().collect { timeAndDate ->
                if (timeAndDate.first != time.value) time.postValue(timeAndDate.first)
                if (timeAndDate.second != date.value) date.postValue(timeAndDate.second)
            }
        }
    }

    fun downloadAndInstallApk(context: Context, filesDir: File) {
        viewModelScope.launch {
            val file = File(filesDir, "artizan_tbilisi.apk")
            file.delete()
            val inputStream = getUpdatedApplicationInputStreamUseCase.getUpdatedApplicationInputStream()
            saveFileFromInputStreamUseCase.saveFileFromInputStream(inputStream, file)
            Log.i("LOL", file.nameWithoutExtension + " " + file.totalSpace.toString())
            Log.i("LOL", file.absolutePath)
            applicationsUseCase.installApk(context, file)
        }
    }
}