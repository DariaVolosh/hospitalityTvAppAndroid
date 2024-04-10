package com.example.hoteltvapptemplate.presenter.rooms

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hoteltvapptemplate.R

@Composable
fun RoomsRow(
    rooms: List<RoomInfo>
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        for (room in rooms) {
            RoomComposable(
                roomInfo = room,
                modifier = Modifier.weight(0.5f)
            )
        }
    }
}

@Composable
fun RoomComposable(
    roomInfo: RoomInfo,
    modifier: Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(vertical = 15.dp),
            text = roomInfo.roomType,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 30.sp,
            fontFamily = FontFamily(Font(R.font.nokoro_regular))
        )

        Image(
            modifier = Modifier.border(4.dp, Color.White),
            painter = painterResource(roomInfo.roomImage),
            contentDescription = stringResource(R.string.room)
        )
    }
}

data class RoomInfo(
    val roomType: String,
    val roomImage: Int
)