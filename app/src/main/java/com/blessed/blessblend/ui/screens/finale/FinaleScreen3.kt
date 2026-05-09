package com.blessed.blessblend.ui.screens.finale

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.blessed.blessblend.R
import com.blessed.blessblend.models.FavoritesManager
import com.blessed.blessblend.navigation.ROUTE_FAVORITES
import com.blessed.blessblend.navigation.ROUTE_HOME
import com.blessed.blessblend.navigation.ROUTE_PROFILE
import com.blessed.blessblend.ui.theme.brown1
import com.blessed.blessblend.ui.theme.peach

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinaleScreen3(
    navController: NavController
) {

    var selectedIndex by remember { mutableIntStateOf(0) }

    val productList = listOf(
        ProductImage(1, R.drawable.finale3a, "Look 1"),
        ProductImage(2, R.drawable.finale3b, "Look 2"),
        ProductImage(3, R.drawable.finale3c, "Look 3"),
        ProductImage(4, R.drawable.finale3d, "Look 4"),
        ProductImage(5, R.drawable.finale3e, "Look 5"),
        ProductImage(6, R.drawable.finale3f, "Look 6"),
        ProductImage(7, R.drawable.finale3g, "Look 7"),
        ProductImage(8, R.drawable.finale3h, "Look 8"),
        ProductImage(9, R.drawable.finale3i, "Look 9"),
        ProductImage(10, R.drawable.finale3j, "Look 10")
    )

    Scaffold(

        topBar = {

            TopAppBar(

                title = {
                    Text(
                        text = "Results",
                        fontWeight = FontWeight.Bold
                    )
                },

                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },

                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Info"
                        )
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = peach,
                    titleContentColor = brown1,
                    navigationIconContentColor = brown1,
                    actionIconContentColor = brown1
                )
            )
        },

        bottomBar = {

            NavigationBar(
                containerColor = peach
            ) {

                NavigationBarItem(
                    icon = {
                        Icon(
                            Icons.Default.Home,
                            contentDescription = "Home"
                        )
                    },
                    label = {
                        Text("Home")
                    },
                    selected = selectedIndex == 0,
                    onClick = {
                        selectedIndex = 0
                        navController.navigate(ROUTE_HOME)
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.White
                    )
                )

                NavigationBarItem(
                    icon = {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = "Profile"
                        )
                    },
                    label = {
                        Text("Profile")
                    },
                    selected = selectedIndex == 2,
                    onClick = {
                        selectedIndex = 2
                        navController.navigate(ROUTE_PROFILE)
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent
                    )
                )

                NavigationBarItem(
                    icon = {
                        Icon(
                            Icons.Default.Favorite,
                            contentDescription = "Favorites"
                        )
                    },
                    label = {
                        Text("Favorites")
                    },
                    selected = selectedIndex == 1,
                    onClick = {
                        selectedIndex = 1
                        navController.navigate(ROUTE_FAVORITES)
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent
                    )
                )
            }
        },

        content = { paddingValues ->

            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .paint(
                        painter = painterResource(R.drawable.img),
                        contentScale = ContentScale.FillBounds
                    )
            ) {

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    items(productList) { product ->

                        val isSaved =
                            FavoritesManager.isFavorite(product)

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(220.dp),

                            shape = RoundedCornerShape(16.dp),

                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {

                            Box {

                                Image(
                                    painter = painterResource(product.resId),
                                    contentDescription = product.label,
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )

                                // FAVORITE BUTTON
                                IconButton(

                                    onClick = {
                                        FavoritesManager.toggleFavorite(product)
                                    },

                                    modifier = Modifier
                                        .align(Alignment.TopEnd)
                                        .padding(8.dp)
                                        .background(
                                            Color.Black.copy(alpha = 0.3f),
                                            shape = RoundedCornerShape(50)
                                        )
                                ) {

                                    Icon(
                                        imageVector =
                                            if (isSaved)
                                                Icons.Default.Favorite
                                            else
                                                Icons.Default.FavoriteBorder,

                                        contentDescription = "Save",

                                        tint =
                                            if (isSaved)
                                                Color.Red
                                            else
                                                Color.White
                                    )
                                }

                                Text(
                                    text = product.label,

                                    modifier = Modifier
                                        .align(Alignment.BottomStart)
                                        .padding(12.dp)
                                        .background(
                                            Color.Black.copy(alpha = 0.5f),
                                            RoundedCornerShape(4.dp)
                                        )
                                        .padding(
                                            horizontal = 8.dp,
                                            vertical = 4.dp
                                        ),

                                    color = Color.White,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun FinaleScreen3Preview() {

    FinaleScreen3(
        rememberNavController()
    )
}