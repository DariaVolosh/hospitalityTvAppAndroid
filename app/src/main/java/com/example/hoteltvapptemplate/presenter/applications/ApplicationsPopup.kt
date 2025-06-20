package com.example.hoteltvapptemplate.presenter.applications

import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.amazonaws.services.dynamodbv2.model.KeyType
import com.example.hoteltvapptemplate.ScreenParameters

@Composable
fun ApplicationsPopup(
    modifier: Modifier,
    screenParameters: ScreenParameters
) {
    val context = LocalContext.current
    var focusedApp by remember { mutableIntStateOf(0) }
    val focusRequester = remember { FocusRequester() }

    val applications = screenParameters.mainScreenViewModels.applicationsViewModel.applicationsList

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(30.dp),
        modifier = modifier
            .focusable()
            .focusRequester(focusRequester)
            .onKeyEvent { keyEvent ->
                if (keyEvent.type == KeyEventType.KeyDown) {
                    when (keyEvent.key) {
                        Key.DirectionRight -> focusedApp++
                        Key.DirectionLeft -> focusedApp--
                        Key.DirectionCenter -> {
                            val applicationName = context.getString(applications[focusedApp].name)
                            screenParameters.mainScreenViewModels.applicationsViewModel.launchExternalApp(
                                applicationName
                            )
                        }
                    }
                }

                true
            }
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(15.dp)
    ) {
        applications.forEachIndexed { index, appInfo ->
            ApplicationIconAndName(
                appInfo,
                index == focusedApp
            )
        }
    }
}