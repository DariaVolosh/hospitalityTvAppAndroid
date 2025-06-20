package com.example.hoteltvapptemplate.presenter.applications

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hoteltvapptemplate.R

@Composable
fun ApplicationIconAndName(
    externalApplicationInfo: ExternalApplicationInfo,
    isFocused: Boolean
) {
    Column(
        modifier = Modifier.border(
            1.dp,
            if (isFocused) MaterialTheme.colorScheme.onPrimaryContainer
            else Color.Transparent
        ).padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Image(
            modifier = Modifier
                .height(51.dp),
            painter = painterResource(externalApplicationInfo.icon),
            contentDescription = stringResource(externalApplicationInfo.name)
        )

        Text(
            text = stringResource(externalApplicationInfo.name),
            fontSize = 22.sp,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}