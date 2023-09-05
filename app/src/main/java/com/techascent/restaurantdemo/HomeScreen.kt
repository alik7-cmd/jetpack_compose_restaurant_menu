package com.techascent.restaurantdemo

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.techascent.restaurantdemo.Category
import com.techascent.restaurantdemo.CategoryTab
import com.techascent.restaurantdemo.FoodItemPager
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryTab(categories: List<Category>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()

    TabRow(selectedTabIndex = pagerState.currentPage) {
        categories.forEachIndexed { index, category ->
            Tab(selected = pagerState.currentPage == index, onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(index)
                }
            }, text = {
                Text(text = category.name)
            })
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FoodItemPager(pagerState: PagerState, navController : NavHostController){
    HorizontalPager(state = pagerState) {
        FoodList(
            foodItems = listOf(
                FoodItem(
                    0,
                    "Burger",
                    "Burger & Sandwich",
                    "https://www.foodiesfeed.com/wp-content/uploads/2023/05/juicy-cheeseburger.jpg"
                ),
                FoodItem(
                    1,
                    "Savoury",
                    "Savoury Awesome!",
                    "https://images.pexels.com/photos/4349774/pexels-photo-4349774.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"
                ),
                FoodItem(
                    3,
                    "Cake",
                    "Beautiful Cake",
                    "https://images.pexels.com/photos/1835765/pexels-photo-1835765.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"
                )
            ), navController
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(categories: List<Category>, navController : NavHostController){
    val pagerState = rememberPagerState(pageCount = {categories.size})
    Column(modifier = Modifier.fillMaxSize()) {
        CategoryTab(categories, pagerState)
        FoodItemPager(pagerState, navController)
    }
}