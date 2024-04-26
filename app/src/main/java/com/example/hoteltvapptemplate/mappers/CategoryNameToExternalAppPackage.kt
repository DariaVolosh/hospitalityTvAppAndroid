package com.example.hoteltvapptemplate.mappers

import android.content.Context
import com.example.hoteltvapptemplate.R
import javax.inject.Inject

class CategoryNameToExternalAppPackage @Inject constructor(
    override var appContext: Context
) : Mapper {

    // mapper from category name list of package name and main activity name pairs
    // for some category names there are multiple corresponding packages
    private var categoryNameToExternalAppPackageMapper = mapOf<String, List<Pair<String, String>>>()

    override fun updateNamesMapper() {
        val newMapper = mapOf(
            getString(R.string.youtube) to
            listOf(
                Pair(
                    getString(R.string.youtube_package_name),
                    getString(R.string.youtube_main_activity_name)
                )
            ),

            getString(R.string.tv_channels) to
            listOf(
                Pair(
                    getString(R.string.ok_tv_package_name),
                    getString(R.string.ok_tv_main_activity_name)
                ),
                Pair(
                    getString(R.string.tivimate_package_name),
                    getString(R.string.tivimate_main_activity_name)
                )
            )
        )

        categoryNameToExternalAppPackageMapper = newMapper
    }

    fun getPackagesList(categoryName: String): List<Pair<String, String>> =
        categoryNameToExternalAppPackageMapper[categoryName] ?: listOf()
}