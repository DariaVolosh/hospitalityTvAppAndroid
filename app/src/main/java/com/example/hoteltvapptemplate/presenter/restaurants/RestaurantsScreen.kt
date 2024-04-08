package com.example.hoteltvapptemplate.presenter.restaurants

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.hoteltvapptemplate.R
import com.example.hoteltvapptemplate.ScreenParameters
import com.example.hoteltvapptemplate.presenter.screen.ScreenBackground

@Composable
fun RestaurantsScreen(
    screenParameters: ScreenParameters
) {
    val curr = screenParameters.defaultParameters.getContext()
    val updatedContext by remember { mutableStateOf(curr) }
    val restaurantsList =
        screenParameters.mainScreenViewModels.restaurantsViewModel.getRestaurantsData()

    ScreenBackground(
        screenParameters,
        updatedContext.resources.getString(R.string.our_restaurants),
        updatedContext,
        { modifier ->
            LazyColumn(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(restaurantsList.size) {index ->
                    val restaurant = restaurantsList[index]

                    RestaurantCard(
                        restaurantName = restaurant.name,
                        restaurantDescription = restaurant.description,
                        openHours = restaurant.openHours,
                        Modifier.fillParentMaxHeight(0.7f)
                    )
                }
            }
        }
    ) {}
}