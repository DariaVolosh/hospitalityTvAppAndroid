package com.example.hoteltvapptemplate.data.repositories

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.content.FileProvider
import com.example.hoteltvapptemplate.mappers.CategoryNameToExternalAppPackage
import java.io.File
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

    fun installApk(context: Context, apkFile: File) {
        val apkUri = FileProvider.getUriForFile(
            context,
            context.packageName + ".fileprovider",
            apkFile
        )

        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(
            apkUri,
            "application/vnd.android.package-archive"
        )

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        context.startActivity(intent)
    }
}