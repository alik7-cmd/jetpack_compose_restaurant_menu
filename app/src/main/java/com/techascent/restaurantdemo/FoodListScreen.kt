package com.techascent.restaurantdemo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun FoodList(foodItems: List<FoodItem>, navHostController: NavHostController) {
    LazyColumn(contentPadding = PaddingValues(16.dp), content = {
        items(foodItems) {
            ItemFoodCard(it, modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .clip(RoundedCornerShape(3.dp)),
                onClick = {
                    navHostController.currentBackStackEntry?.savedStateHandle?.set(
                        "data",
                        it
                    )
                    navHostController.navigate(Screen.Details.route)
                })
        }
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemFoodCard(item: FoodItem, modifier: Modifier = Modifier, onClick: () -> Unit) {

    Card(
        onClick = onClick,
        modifier = modifier,
        shape = CardDefaults.shape,
        elevation = CardDefaults.cardElevation()
    ) {
        Row {
            val mod = Modifier
                .size(80.dp, 80.dp)
                .clip(RoundedCornerShape(16.dp))

            LoadImageFromUrl(
                url = item.image,
                contentDescription = item.name,
                modifier = mod
            )
            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(
                    text = item.name,
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = item.description,
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                    fontSize = 15.sp,
                    style = MaterialTheme.typography.displaySmall
                )

            }

        }
    }

}