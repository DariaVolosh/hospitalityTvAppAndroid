package com.example.hoteltvapptemplate.presenter.hotelInfo

import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.hoteltvapptemplate.R
import com.example.hoteltvapptemplate.ScreenParameters
import com.example.hoteltvapptemplate.presenter.screen.ScreenBackground
import kotlinx.coroutines.delay

@Composable
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
    )

@Composable
fun HotelInfoScreen(screenParameters: ScreenParameters) {
    val curr = screenParameters.defaultParameters.getContext()
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
}