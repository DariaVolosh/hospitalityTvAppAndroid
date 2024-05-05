package com.example.hoteltvapptemplate.presenter.screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hoteltvapptemplate.useCases.GetTimeAndDateUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScreenViewModel @Inject constructor(
    private val getTimeAndDateUseCase: GetTimeAndDateUseCase
): ViewModel() {
    val time = MutableLiveData("")
    val date = MutableLiveData("")

    // isWelcomeScreen variable used to detect if categories row should be expanded by default
    // (which is only on welcome screen) and on other screens it should not be expanded
    var isWelcomeScreen = MutableLiveData(true)

    init {
        /* viewModelScope.launch {
            getTimeAndDateUseCase.getTimeAndDateFlow().collect { timeAndDate ->
                if (timeAndDate.first != time.value) time.postValue(timeAndDate.first)
                if (timeAndDate.second != date.value) date.postValue(timeAndDate.second)
            }
        } */
    }
}