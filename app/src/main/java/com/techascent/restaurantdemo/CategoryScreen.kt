package com.techascent.restaurantdemo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun CategoryList(categories: List<Category>, navHostController: NavHostController) {
    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 250.dp),
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 16.dp,
            end = 12.dp,
            bottom = 16.dp
        ),

        content = {
            items(categories) {
                ItemCategoryCard(
                    it, modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth(),
                    onClick = {
                        //navHostController.navigate(Screen.Food.route)
                    }
                )
            }
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemCategoryCard(category: Category, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = modifier,
        shape = CardDefaults.shape,
        colors = CardDefaults.cardColors(),
        elevation = CardDefaults.cardElevation(),
    ) {

        Column {

            val modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5F)

            LoadImageFromUrl(
                url = category.image,
                contentDescription = category.name,
                modifier = modifier
            )

            TextField(
                text = category.name,
                fontWeight = FontWeight.SemiBold,
                fontSize = 35.sp,
                color = MaterialTheme.colorScheme.surface,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp)
            )
        }

    }
}