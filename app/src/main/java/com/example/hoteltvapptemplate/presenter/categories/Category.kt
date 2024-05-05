package com.example.hoteltvapptemplate.presenter.categories

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.hoteltvapptemplate.R

@Composable
fun Category(
    name: String,
    @DrawableRes icon: Int,
    modifier: Modifier,
    navigateToCategory: () -> Unit
) {

    var isHovering by remember { mutableStateOf(false) }
    val border = if (isHovering) modifier.border(1.dp, Color.White) else modifier

    Row(
        modifier = border
            .background(
                if (isHovering) MaterialTheme.colorScheme.secondaryContainer
                else MaterialTheme.colorScheme.primaryContainer
            ).onFocusChanged {
                isHovering = it.isFocused
            }
            .focusable()
            .clickable { navigateToCategory() },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxHeight()
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = name,
                modifier = Modifier.size(40.dp)
            )

            if (name != "") {
                Text(
                    text = name,
                    fontFamily = FontFamily(Font(R.font.nokora_light)),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = TextUnit(17f, TextUnitType.Sp),
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth(0.8f),
                    textAlign = TextAlign.Center,
                )
            }
        }

        if (name != "") {
            VerticalDivider(
                thickness = 1.dp,
                color = Color.White
            )
        }
    }
}