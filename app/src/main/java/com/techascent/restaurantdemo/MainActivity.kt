package com.techascent.restaurantdemo

import android.os.Bundle
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
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
                CategoryCard(
                    it, modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth(),
                    onClick = {
                        navHostController.currentBackStackEntry?.savedStateHandle?.set(
                            "data",
                            it
                        )
                        navHostController.navigate(Screen.Details.route)
                    }
                )
            }
        })
}

@Composable
fun FoodList(foodItems: List<FoodItem>){

    LazyColumn(contentPadding = PaddingValues(16.dp), content = {

        items(foodItems){

        }
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryCard(category: Category, modifier: Modifier = Modifier, onClick: () -> Unit) {
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
        navHostController.previousBackStackEntry?.savedStateHandle?.get<Category>("data")
            ?: Category()

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
    }

}


