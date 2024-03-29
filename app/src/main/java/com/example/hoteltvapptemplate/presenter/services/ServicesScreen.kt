package com.example.hoteltvapptemplate.presenter.services

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.hoteltvapptemplate.R
import com.example.hoteltvapptemplate.presenter.screen.ScreenBackground
import com.example.hoteltvapptemplate.presenter.screen.ScreenViewModel

@Composable
fun ServicesScreen(
    screenViewModel: ScreenViewModel,
    navigateToCategory: (String) -> Unit,
    getContext: () -> Context
) {
    val curr = getContext()
    val updatedContext by remember { mutableStateOf(curr) }

    ScreenBackground(
        screenViewModel,
        updatedContext.resources.getString(R.string.variety_of_services),
        navigateToCategory,
        updatedContext,
        {}
    ) {}
}