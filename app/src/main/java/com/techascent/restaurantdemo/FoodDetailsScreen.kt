package com.techascent.restaurantdemo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
        colors = CardDefaults.cardColors()
    ) {

        Column {
            LoadImageFromUrl(
                url = data.image,
                contentDescription = data.name,
                modifier = Modifier
                    .wrapContentSize(Alignment.Center)
                    .clip(RoundedCornerShape(3.dp))

            )

            Text(text = data.name, modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp), fontSize = 30.sp, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.width(16.dp))

            Text(text = stringResource(id = R.string.big_text), modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp), fontSize = 15.sp, style = MaterialTheme.typography.labelLarge)
            
            Spacer(modifier = Modifier.width(10.dp))

            Row(modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.End) {
                Button(onClick = { }, modifier = Modifier.padding(2.dp)) {
                    Image(painter = painterResource(id =  R.drawable.baseline_add_24), contentDescription = "Add" )
                }
                Button(onClick = { }, modifier = Modifier.padding(2.dp)) {
                    Image(painter = painterResource(id =  R.drawable.baseline_remove_24), contentDescription = "Add" )
                }
            }

        }

    }

}