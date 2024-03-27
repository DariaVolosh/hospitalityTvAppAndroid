package com.example.hoteltvapptemplate.presenter.services

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.hoteltvapptemplate.R
import com.example.hoteltvapptemplate.presenter.screen.ScreenBackground
import com.example.hoteltvapptemplate.presenter.screen.ScreenViewModel

@Composable
fun ServicesScreen(
    screenViewModel: ScreenViewModel,
    navigateToCategory: (String) -> Unit
) {
    val curr = LocalContext.current
    val updatedContext by remember { mutableStateOf(curr) }

    ScreenBackground(
        screenViewModel,
        updatedContext.resources.getString(R.string.variety_of_services),
        navigateToCategory,
        updatedContext,
        {}
    ) {}
}