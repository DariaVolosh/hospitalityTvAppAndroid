package com.example.hoteltvapptemplate.presenter.categories

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.hoteltvapptemplate.mappers.CategoriesMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoriesViewModel @Inject constructor(
    private val categoriesMapper: CategoriesMapper
): ViewModel() {
    fun mapScreenName(categoryName: String) = categoriesMapper.mapScreenName(categoryName)
    fun mapCategoryIcon(categoryName: String) = categoriesMapper.mapIcon(categoryName)
    fun getCategoriesNames() = categoriesMapper.getCategoriesNames()
    fun setMapperContext(context: Context) = categoriesMapper.setContext(context)
}