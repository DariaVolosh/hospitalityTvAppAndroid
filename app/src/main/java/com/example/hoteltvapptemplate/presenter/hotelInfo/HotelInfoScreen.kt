package com.example.hoteltvapptemplate.presenter.hotelInfo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.hoteltvapptemplate.R
import com.example.hoteltvapptemplate.ScreenParameters
import com.example.hoteltvapptemplate.presenter.screen.ScreenBackground
import com.example.hoteltvapptemplate.ui.theme.md_theme_dark_black_transparent

/* @Composable
fun getTextOffset(initial: Int) = animateIntOffsetAsState(
        targetValue = IntOffset(
            when (initial) {
                2 -> 5000
                1 -> 0
                else -> -5000
            }, 0
        ),
        label = stringResource(R.string.offset),
        animationSpec = tween(durationMillis = 1000)
    ) */
/* @Composable
fun HotelInfoScreen(screenParameters: ScreenParameters) {
    val curr = screenParameters.mainScreenViewModels.applicationsViewModel.getContext()
    val updatedContext by remember { mutableStateOf(curr) }

    var headers by remember { mutableStateOf(listOf<String>()) }
    var descriptions by remember { mutableStateOf(listOf<String>()) }
    var currentTopicIndex by remember { mutableIntStateOf(0) }
    var isTopicVisible by remember { mutableStateOf(false) }

    var initial by remember { mutableIntStateOf(2) }
    val offsetState = getTextOffset(initial)

    val isActive = true

    val hotelInfoViewModel = screenParameters.mainScreenViewModels.hotelInfoViewModel
    
    LaunchedEffect(key1 = updatedContext) {
        hotelInfoViewModel.setMapperContext(updatedContext)
        headers = hotelInfoViewModel.getHeaders().toList()
        descriptions = hotelInfoViewModel.getDescriptions().toList()
    }

    LaunchedEffect(Unit) {
        while (isActive) {
            initial = 2
            delay(1000)
            isTopicVisible = true
            initial = 1
            delay(10000)
            initial = 0
            delay(1000)
            isTopicVisible = false
            if (currentTopicIndex == headers.size-1) currentTopicIndex = 0
            else currentTopicIndex++
        }
    }

    ScreenBackground(
        screenParameters,
        headerText = if (headers.isNotEmpty()) headers[currentTopicIndex] else "",
        updatedContext = updatedContext,
        mainContent = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.left_arrow),
                    contentDescription = stringResource(R.string.left_arrow),
                    modifier = Modifier
                        .padding(horizontal = 25.dp)
                        .size(35.dp)
                )

                HotelInfoDescription(
                    text = if (descriptions.isNotEmpty()) descriptions[currentTopicIndex] else "",
                    modifier = Modifier.weight(1f),
                    offsetState.value,
                    isTopicVisible
                )

                Image(
                    painter = painterResource(id = R.drawable.right_arrow),
                    contentDescription = stringResource(R.string.right_arrow),
                    modifier = Modifier
                        .padding(horizontal = 25.dp)
                        .size(35.dp)
                )
            }
        }
    ) {}
} */


@Composable
fun HotelInfoScreen(
    screenParameters: ScreenParameters
) {
    val curr = screenParameters.mainScreenViewModels.applicationsViewModel.get().getContext()
    val updatedContext by remember { mutableStateOf(curr) }

    var isReadyToPlay by remember { mutableStateOf(false) }

    val myPlayer = ExoPlayer
        .Builder(LocalContext.current)
        .build()

    LaunchedEffect(Unit) {
        myPlayer.setMediaItem(MediaItem.fromUri("asset:///hotel_video.mp4"))
        myPlayer.repeatMode = Player.REPEAT_MODE_ONE

        myPlayer.addListener(object: Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                if (playbackState == 3) isReadyToPlay = true
                super.onPlaybackStateChanged(playbackState)
            }
        })
        myPlayer.prepare()
        myPlayer.play()
    }

    DisposableEffect(Unit) {
        onDispose {
            myPlayer.release()
        }
    }

    val brush = Brush.verticalGradient(
        listOf(
            md_theme_dark_black_transparent,
            md_theme_dark_black_transparent
        )
    )

    ScreenBackground(
        screenParameters,
        headerText = updatedContext.getString(R.string.welcome_to_hotel),
        updatedContext = updatedContext,
        brush,
        mainContent = {
            Box(
                modifier = it
                    .padding(bottom = 30.dp)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                AndroidView(factory = {
                    context -> PlayerView(context).apply {
                        player = myPlayer
                        useController = false
                    }
                })

                if (!isReadyToPlay) CircularProgressIndicator()
            }
        }
    ) {}
}