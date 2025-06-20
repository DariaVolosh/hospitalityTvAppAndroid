package com.example.hoteltvapptemplate.presenter.applications

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.hoteltvapptemplate.ScreenParameters
import com.example.hoteltvapptemplate.presenter.screen.ScreenBackground

@Composable
fun ApplicationsScreen(
    screenParameters: ScreenParameters
) {
    val curr = screenParameters.mainScreenViewModels.applicationsViewModel.getContext()
    val updatedContext by remember { mutableStateOf(curr) }

    ScreenBackground(
        screenParameters = screenParameters,
        headerText = "",
        updatedContext = updatedContext,
        mainContent = { _ ->
            ApplicationsPopup(
                Modifier,
                screenParameters
            )
        }
    ) {}
}