package com.example.hoteltvapptemplate.presenter

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hoteltvapptemplate.R

@Composable
fun CategoriesRow(context: Context) {
    val categories = mutableMapOf(
        context.resources.getString(R.string.tv_channels) to R.drawable.tv_channels,
        context.resources.getString(R.string.our_services) to R.drawable.our_services,
        context.resources.getString(R.string.weather) to R.drawable.weather,
        context.resources.getString(R.string.hotel_info) to R.drawable.hotel_info,
        context.resources.getString(R.string.restaurants) to R.drawable.restaurants,
        context.resources.getString(R.string.rooms) to R.drawable.rooms
    )

    Row(
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        for (c in categories.entries) {
            Category(
                name = c.key,
                icon = c.value,
                modifier = Modifier.weight(1f)
            )
        }
    }
}