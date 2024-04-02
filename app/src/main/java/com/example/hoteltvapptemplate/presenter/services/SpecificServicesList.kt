package com.example.hoteltvapptemplate.presenter.services

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hoteltvapptemplate.R

@Composable

fun SpecificServicesList(
    serviceName: String,
    specificServiceName: List<String>
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .padding(25.dp)
    ) {
        Text(
            text = serviceName,
            fontSize = 22.sp,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier
                .padding(end = 25.dp)
        )

        Column {
            for (specService in specificServiceName) {
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