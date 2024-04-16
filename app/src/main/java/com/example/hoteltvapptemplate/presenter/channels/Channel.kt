package com.example.hoteltvapptemplate.presenter.channels

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.hoteltvapptemplate.R

@Composable
fun Channel(
    channelNumber: Int,
    channelLogoURL: String,
    channelName: String,
    navigateToChannel: () -> Unit
) {
    var isFocused by rememberSaveable { mutableStateOf(false) }
    Box(
        modifier = Modifier.background(
            if (isFocused) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.primaryContainer)
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .onFocusChanged { isFocused = it.isFocused }
            .focusable()
            .clickable { navigateToChannel() }
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
        ){
            Text(
                text = channelNumber.toString(),
                fontSize = 15.sp,
                color =
                if (isFocused)
                    MaterialTheme.colorScheme.onPrimary
                else
                    MaterialTheme.colorScheme.onPrimaryContainer
            )

            Box(
                modifier = Modifier
                    .width(70.dp)
                    .height(70.dp),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = channelLogoURL,
                    contentDescription = stringResource(R.string.channel_logo_text),
                    modifier = Modifier.padding(horizontal = 15.dp)
                )
            }

            Text(
                text = channelName,
                fontSize = 15.sp,
                color =
                if (isFocused)
                    MaterialTheme.colorScheme.onPrimary
                else
                    MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}