package com.example.hoteltvapptemplate.presenter.rooms

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.unit.dp
import com.example.hoteltvapptemplate.R
import com.example.hoteltvapptemplate.ScreenParameters
import com.example.hoteltvapptemplate.presenter.screen.ScreenBackground
import kotlinx.coroutines.launch

@Composable
fun RoomsScreen(
    screenParameters: ScreenParameters
) {
    val curr = screenParameters.defaultParameters.getContext()
    val updatedContext by remember { mutableStateOf(curr) }

    val roomsList = screenParameters.mainScreenViewModels.roomsViewModel.getRoomsData()
    var roomsRow = 0

    val scrollState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    ScreenBackground(
        screenParameters,
        updatedContext.getString(R.string.rooms),
        updatedContext,
        { modifier ->
            LazyColumn(
                state = scrollState,
                modifier = modifier
                    .padding(
                        start = 30.dp,
                        end = 30.dp,
                        bottom = 30.dp
                    )
                    .focusable()
                    .onKeyEvent {
                        when (it.key) {
                            Key.DirectionUp -> {
                                if (roomsRow > 0) {
                                    scope.launch {
                                        scrollState.animateScrollToItem(roomsRow - 1)
                                        roomsRow--
                                    }
                                    true
                                } else {
                                    false
                                }
                            }

                            Key.DirectionDown -> {
                                if (roomsRow + 1 < roomsList.size / 2) {
                                    scope.launch {
                                        scrollState.animateScrollToItem(roomsRow+1)
                                        roomsRow++
                                    }
                                    true
                                } else {
                                    false
                                }
                            }

                            else -> false
                        }
                    }
            ) {
                items(roomsList.size / 2) { index ->
                    val startIndex = index * 2
                    RoomsRow(roomsList.subList(startIndex, startIndex + 2))
                }
            }
        }
    ) {}
}