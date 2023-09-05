package com.techascent.restaurantdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.techascent.restaurantdemo.ui.theme.RestaurantDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RestaurantDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ComposeNavigation()
                }
            }
        }
    }
}


@Composable
fun ComposeNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(categories = listOf(
                Category(
                    0L,
                    "Popular",
                    "https://www.foodiesfeed.com/wp-content/uploads/2023/05/juicy-cheeseburger.jpg"
                ),
                Category(
                    0L,
                    "Savoury",
                    "https://images.pexels.com/photos/4349774/pexels-photo-4349774.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"
                ),
                Category(
                    0L,
                    "Burger",
                    "https://www.foodiesfeed.com/wp-content/uploads/2023/05/juicy-cheeseburger.jpg"
                ),
                Category(
                    0L,
                    "Cake",
                    "https://images.pexels.com/photos/1835765/pexels-photo-1835765.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"
                ),
                Category(
                    0L,
                    "Pizza",
                    "https://images.pexels.com/photos/6072095/pexels-photo-6072095.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"
                )), navController = navController)
        }
        composable(Screen.Details.route) {
            DetailsScreen(navController)
        }

        composable(Screen.Food.route) {
            FoodList(
                foodItems = listOf(
                    FoodItem(
                        0,
                        "Burger",
                        "Burger",
                        "https://www.foodiesfeed.com/wp-content/uploads/2023/05/juicy-cheeseburger.jpg"
                    ),
                    FoodItem(
                        1,
                        "Burger",
                        "",
                        "https://www.foodiesfeed.com/wp-content/uploads/2023/05/juicy-cheeseburger.jpg"
                    ),
                    FoodItem(
                        3,
                        "Burger",
                        "Burger",
                        "https://www.foodiesfeed.com/wp-content/uploads/2023/05/juicy-cheeseburger.jpg"
                    )
                ), navController
            )
        }
    }

}


