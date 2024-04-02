package com.example.hoteltvapptemplate.presenter.hotelInfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.hoteltvapptemplate.R

@Composable
fun HotelInfoDescription(
    text: String,
    modifier: Modifier,
    offset: IntOffset,
    visible: Boolean
) {
    Text(
        modifier = modifier
            .alpha(if (visible) 1f else 0f)
            .offset { offset }
            .background(MaterialTheme.colorScheme.primary)
            .padding(25.dp)
        ,
        text = text,
        color = MaterialTheme.colorScheme.onPrimary,
        fontFamily = FontFamily(Font(R.font.nokora_light))
    )
}