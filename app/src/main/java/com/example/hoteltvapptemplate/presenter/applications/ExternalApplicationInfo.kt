package com.example.hoteltvapptemplate.presenter.applications

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ExternalApplicationInfo(
    @DrawableRes val icon: Int,
    @StringRes val name: Int
)
