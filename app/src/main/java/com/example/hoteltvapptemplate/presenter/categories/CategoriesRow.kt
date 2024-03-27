package com.example.hoteltvapptemplate.presenter.categories

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CategoriesRow(
    updatedContext: Context,
    getCategoriesNames: () -> Set<String>,
    mapCategoryIcon: (String) -> Int,
    mapScreenName: (String) -> String,
    setContext: (Context) -> Unit,
    navigateToCategory: (String) -> Unit
) {

    var currentNames by remember {
        mutableStateOf(getCategoriesNames())
    }

    LaunchedEffect(key1 = updatedContext) {
        setContext(updatedContext)
        currentNames = getCategoriesNames()
    }

    Row(
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        for (c in currentNames) {
            Category(
                name = c,
                icon = mapCategoryIcon(c),
                modifier = Modifier.weight(1f),
            ) {
                navigateToCategory(mapScreenName(c))
            }
        }
    }
}