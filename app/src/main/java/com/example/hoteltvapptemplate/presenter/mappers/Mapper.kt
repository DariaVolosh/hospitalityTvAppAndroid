package com.example.hoteltvapptemplate.presenter.mappers

import android.content.Context

interface Mapper {
    var appContext: Context
    fun updateNamesMapper()
    fun setContext(newContext: Context) {
        appContext = newContext
        updateNamesMapper()
    }

    fun getString(res: Int) = appContext.resources.getString(res)
}