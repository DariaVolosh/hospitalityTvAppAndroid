package com.example.hoteltvapptemplate.presenter.restaurants

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import com.example.hoteltvapptemplate.R
import com.example.hoteltvapptemplate.ScreenParameters
import com.example.hoteltvapptemplate.presenter.screen.ScreenBackground
import kotlinx.coroutines.launch

@Composable
fun RestaurantsScreen(
    screenParameters: ScreenParameters
) {
    val curr = screenParameters.mainScreenViewModels.applicationsViewModel.get().getContext()
    val updatedContext by remember { mutableStateOf(curr) }
    var restaurantsPhotos by remember { mutableStateOf<List<Int>>(listOf()) }
    var restaurantsInformation by remember { mutableStateOf<List<Restaurant>>(listOf()) }

    val restaurantsViewModel = screenParameters.mainScreenViewModels.restaurantsViewModel

    val isCategoriesFocused by screenParameters
        .mainScreenViewModels.categoriesViewModel.get().isFocused.observeAsState()

    LaunchedEffect(updatedContext) {
        restaurantsViewModel.get().setMapperContext(updatedContext)
        restaurantsPhotos = restaurantsViewModel.get().getPhotos().toList()
        restaurantsInformation = restaurantsViewModel.get().getRestaurantsInformation().toList()
    }

    val scrollState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    var currentRestaurant = 0

    ScreenBackground(
        screenParameters,
        updatedContext.resources.getString(R.string.our_restaurants),
        updatedContext,
        null,
        { modifier ->
            LazyColumn(
                state = scrollState,
                modifier = modifier
                    .focusable()
                    .onKeyEvent {
                        when (it.key) {
                            Key.DirectionDown -> {
                                if (currentRestaurant + 1 < restaurantsInformation.size) {
                                    scope.launch {
                                        scrollState.animateScrollToItem(currentRestaurant + 1)
                                        currentRestaurant++
                                    }

                                    true
                                } else {
                                    false
                                }
                            }

                            Key.DirectionUp -> {
                                if (currentRestaurant > 0) {
                                    scope.launch {
                                        scrollState.animateScrollToItem(currentRestaurant - 1)
                                        currentRestaurant--
                                    }
                                    true
                                } else {
                                    false
                                }
                            }

                            else -> false
                        }
                    }
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(restaurantsInformation.size) {index ->
                    val restaurant = restaurantsInformation[index]

                    RestaurantCard(
                        restaurantName = restaurant.name,
                        restaurantDescription = restaurant.description,
                        openHours = restaurant.openHours,
                        restaurantsPhotos[index],
                        Modifier.fillParentMaxHeight(
                            if (isCategoriesFocused == true) 1f else 0.75f
                        )
                    )
                }
            }
        }
    ) {}
}