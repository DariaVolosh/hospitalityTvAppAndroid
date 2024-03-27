package com.example.hoteltvapptemplate.presenter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hoteltvapptemplate.useCases.GetTimeAndDateUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ScreenViewModel @Inject constructor(
    private val getTimeAndDateUseCase: GetTimeAndDateUseCase
): ViewModel() {
    val time = MutableLiveData("")
    val date = MutableLiveData("")

    init {
        viewModelScope.launch {
            getTimeAndDateUseCase.getTimeAndDateFlow().collect { timeAndDate ->
                if (timeAndDate.first != time.value) time.postValue(timeAndDate.first)
                if (timeAndDate.second != date.value) date.postValue(timeAndDate.second)
            }
        }
    }
}