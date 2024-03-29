package com.example.hoteltvapptemplate.presenter.screen

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hoteltvapptemplate.presenter.mappers.CategoriesMapper
import com.example.hoteltvapptemplate.useCases.GetTimeAndDateUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScreenViewModel @Inject constructor(
    private val getTimeAndDateUseCase: GetTimeAndDateUseCase,
    private val categoriesMapper: CategoriesMapper
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

    fun mapScreenName(categoryName: String) = categoriesMapper.mapScreenName(categoryName)
    fun mapCategoryIcon(categoryName: String) = categoriesMapper.mapIcon(categoryName)
    fun getCategoriesNames() = categoriesMapper.getCategoriesNames()
    fun setMapperContext(context: Context) = categoriesMapper.setContext(context)
}