package com.example.hoteltvapptemplate.presenter.categories

import android.content.Context
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hoteltvapptemplate.presenter.screen.ScreenViewModel

@Composable
fun CategoriesRow(
    updatedContext: Context,
    screenViewModel: ScreenViewModel,
    categoriesViewModel: CategoriesViewModel,
    navigateToCategory: (String) -> Unit,
) {

    var currentNames by remember {
        mutableStateOf<Set<String>>(setOf())
    }

    LaunchedEffect(key1 = updatedContext) {
        categoriesViewModel.setMapperContext(updatedContext)
        currentNames = categoriesViewModel.getCategoriesNames()
    }

    val expanded by screenViewModel.isWelcomeScreen.observeAsState()

    Row(
        modifier = Modifier
            .animateContentSize()
            .height(if (expanded == true) 150.dp else 75.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer),
        horizontalArrangement = if (expanded == true) Arrangement.SpaceBetween
                                else Arrangement.Center
    ) {
        for (c in currentNames) {
            Category(
                name = if (expanded == true) c else "",
                icon = categoriesViewModel.mapCategoryIcon(c),
                modifier = if (expanded == true) Modifier.weight(1f)
                           else Modifier.padding(horizontal = 8.dp),
            ) {
                navigateToCategory(categoriesViewModel.mapScreenName(c))
            }
        }
    }
}
