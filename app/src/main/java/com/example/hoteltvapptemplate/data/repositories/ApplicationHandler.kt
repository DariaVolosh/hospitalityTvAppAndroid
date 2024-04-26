package com.example.hoteltvapptemplate.data.repositories

import android.content.Context
import com.example.hoteltvapptemplate.MyApplication
import javax.inject.Inject

class ApplicationHandler @Inject constructor(
    private val application: MyApplication
) {
    fun getContext() = application.getContext()

    fun updateContext(context: Context) {
        application.updateContext(context)
    }
}