package com.techascent.restaurantdemo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun DetailsScreen(navHostController: NavHostController) {

    val data =
        navHostController.previousBackStackEntry?.savedStateHandle?.get<FoodItem>("data")
            ?: FoodItem()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {

        Column {
            LoadImageFromUrl(
                url = data.image,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()

            )
            TextField(
                text = data.name, modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, AlertDialogDefaults.shape)
                    .padding(10.dp), fontWeight = FontWeight.Normal, fontSize = 25.sp
            )

        }

    }

}