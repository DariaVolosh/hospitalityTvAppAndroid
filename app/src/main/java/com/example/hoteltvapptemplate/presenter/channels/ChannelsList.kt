package com.example.hoteltvapptemplate.presenter.channels

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.hoteltvapptemplate.R
import com.example.hoteltvapptemplate.ScreenParameters
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun ChannelsList(screenParameters: ScreenParameters) {
    val channels by screenParameters.mainScreenViewModels.playlistURLViewModel.channels.observeAsState()
    val curr = screenParameters.defaultParameters.getContext()
    val updatedContext by remember { mutableStateOf(curr) }

    Row {
        channels?.let {channels ->
            LazyColumn(
                modifier = Modifier
                    .weight(0.5F)
                    .background(MaterialTheme.colorScheme.primaryContainer)
            ) {
                items(channels.size) {index ->
                    val channel = channels[index]
                    Channel(
                        channelNumber = index+1,
                        channelLogoURL = channel.tvLogoUrl,
                        channelName = channel.channelName
                    ) {
                        screenParameters.defaultParameters.navigateToCategory(
                            "${updatedContext.getString(R.string.watch_channel_screen)}/" +
                                    "${URLEncoder.encode(channel.channelURL ,StandardCharsets.UTF_8.toString())}"
                        )
                    }
                }
            }
        }
    }
}