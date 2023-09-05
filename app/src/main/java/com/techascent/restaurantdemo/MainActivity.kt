package com.techascent.restaurantdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
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
fun StoreImage(@DrawableRes id: Int) {
    Image(
        painter = painterResource(id = id),
        contentDescription = stringResource(id = R.string.app_name)
    )
}

@Composable
fun CategoryList(categories: List<Category>, navHostController: NavHostController) {
    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 400.dp),
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
                        navHostController.navigate(Screen.Food.route)
                    }
                )
            }
        })
}

@Composable
fun FoodList(foodItems: List<FoodItem>, navHostController: NavHostController) {
    LazyColumn(contentPadding = PaddingValues(16.dp), content = {
        items(foodItems) {
            ItemFoodCard(it, modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .clip(RoundedCornerShape(16.dp)),
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
        colors = CardDefaults.cardColors(containerColor = Color.White),
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
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    style = typography.titleMedium
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = item.description,
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                    color = MaterialTheme.colorScheme.surface,
                    style = typography.displaySmall
                )

            }

        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemCategoryCard(category: Category, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = modifier,
        shape = CardDefaults.shape,
        colors = CardDefaults.cardColors(containerColor = Color.White),
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
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp)
            )
        }

    }
}

@Composable
fun LoadImageFromUrl(url: String, contentDescription: String, modifier: Modifier = Modifier) {
    Image(
        painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url/*"https://www.foodiesfeed.com/wp-content/uploads/2023/05/juicy-cheeseburger.jpg"*/)
                .size(coil.size.Size.ORIGINAL) // Set the target size to load the image at.
                .build()
        ),
        contentDescription = contentDescription, //category.name,
        modifier = modifier,
        contentScale = ContentScale.Crop,
        alignment = Alignment.TopCenter
    )
}

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
                    .background(Color.White, shape)
                    .padding(10.dp), fontWeight = FontWeight.Normal, fontSize = 25.sp
            )

        }

    }

}

@Composable
fun TextField(
    text: String,
    fontWeight: FontWeight? = null,
    fontSize: TextUnit = TextUnit.Unspecified,
    color: Color = Color.Unspecified,
    textAlign: TextAlign? = null,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontWeight = fontWeight,
        fontSize = fontSize,
        color = color,
        textAlign = textAlign,
        modifier = modifier
    )
}

@Composable
fun ComposeNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Category.route) {
        composable(Screen.Category.route) {
            CategoryList(
                listOf(
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
                        "Burger & Sandwich",
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
                    ),
                ), navController
            )
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


