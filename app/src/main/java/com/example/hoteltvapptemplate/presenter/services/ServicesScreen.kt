package com.example.hoteltvapptemplate.presenter.services

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hoteltvapptemplate.R
import com.example.hoteltvapptemplate.presenter.categories.CategoriesViewModel
import com.example.hoteltvapptemplate.presenter.screen.ScreenBackground
import com.example.hoteltvapptemplate.presenter.screen.ScreenViewModel
import kotlinx.coroutines.launch

@Composable
fun ServicesScreen(
    screenViewModel: ScreenViewModel,
    categoriesViewModel: CategoriesViewModel,
    servicesViewModel: ServicesViewModel,
    navigateToCategory: (String) -> Unit,
    getContext: () -> Context
) {
    val curr = getContext()
    val updatedContext by remember { mutableStateOf(curr) }
    var services by remember { mutableStateOf(listOf<String>()) }
    var specificServices by remember { mutableStateOf(listOf<List<String>>()) }

    LaunchedEffect(key1 = updatedContext) {
        servicesViewModel.setMapperContext(updatedContext)
        services = servicesViewModel.getServices().toList()
        specificServices = servicesViewModel.getSpecificServices().toList()
    }

    val scrollState = rememberLazyListState()

    // Handle key events for scrolling
    val interactionSource = remember { MutableInteractionSource() }
    var currentService = 0
    val scope = rememberCoroutineScope()

    ScreenBackground(
        screenViewModel,
        categoriesViewModel,
        updatedContext.resources.getString(R.string.variety_of_services),
        navigateToCategory,
        updatedContext,
        {modifier ->
            LazyColumn(
                state = scrollState,
                modifier = modifier
                    .padding(vertical = 10.dp)
                    .focusable(interactionSource = interactionSource)
                    .onKeyEvent {
                        when (it.key) {
                            Key.DirectionUp -> {
                                if (currentService > 0) {
                                    scope.launch {
                                        scrollState.animateScrollToItem(currentService - 1)
                                        currentService--
                                    }
                                    true
                                } else {
                                    false
                                }
                            }

                            Key.DirectionDown -> {
                                if ((currentService + 1) < services.size) {
                                    scope.launch {
                                        scrollState.animateScrollToItem(currentService + 1)
                                        currentService++
                                    }
                                    true
                                } else {
                                    false
                                }
                            }

                            else -> false
                        }
                    }
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primaryContainer)
            ) {
                items(services.size) {serviceI ->
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                            .padding(25.dp)
                    ) {
                        Text(
                            text = services[serviceI],
                            fontSize = 22.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier
                                .padding(end = 25.dp)
                        )

                        Column {
                            for (specService in specificServices[serviceI]) {
                                Text(
                                    text = specService,
                                    fontSize = 17.sp,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    fontFamily = FontFamily(Font(R.font.nokora_light))
                                )
                            }
                        }
                    }
                }
            }
        }
    ) {}
}