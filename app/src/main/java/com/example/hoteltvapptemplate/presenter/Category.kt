package com.example.hoteltvapptemplate.presenter

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.hoteltvapptemplate.R

@Composable
fun Category(
    name: String,
    @DrawableRes icon: Int,
    modifier: Modifier
) {
    Row(
        modifier = modifier
            .fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = name,
                modifier = Modifier.size(40.dp)
            )

            Text(
                text = name,
                fontFamily = FontFamily(Font(R.font.nokora_light)),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontSize = TextUnit(17f, TextUnitType.Sp),
                modifier = Modifier.padding(top = 10.dp),
            )
        }

        VerticalDivider(
            thickness = 1.dp,
            color = Color.White
        )
    }
}