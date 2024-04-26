package com.example.hoteltvapptemplate.data.repositories

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import com.example.hoteltvapptemplate.mappers.CategoryNameToExternalAppPackage
import javax.inject.Inject

class ExternalAppLauncher @Inject constructor(
    private val categoryNameToExternalAppPackage: CategoryNameToExternalAppPackage
) {
    init {
        categoryNameToExternalAppPackage.updateNamesMapper()
    }

    fun launchExternalApp(categoryName: String, context: Context) {
        val packageManager = context.packageManager
        val intent = Intent()
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val packagesList = categoryNameToExternalAppPackage.getPackagesList(categoryName)

        if (packagesList.size == 1) {
            intent.setClassName(packagesList[0].first, packagesList[0].second)
        } else {
            for (p in packagesList) {
                try {
                    packageManager.getApplicationInfo(p.first, PackageManager.GET_META_DATA)
                    intent.setClassName(p.first, p.second)
                    break
                } catch (e: PackageManager.NameNotFoundException) { }
            }
        }

        context.startActivity(intent)
    }
}