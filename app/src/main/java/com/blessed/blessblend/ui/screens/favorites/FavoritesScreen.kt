package com.blessed.blessblend.ui.screens.favorites

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.blessed.blessblend.R
import com.blessed.blessblend.navigation.ROUTE_FAVORITES
import com.blessed.blessblend.navigation.ROUTE_HOME
import com.blessed.blessblend.navigation.ROUTE_PROFILE
import com.blessed.blessblend.ui.screens.finale.FavoritesViewModel
import com.blessed.blessblend.ui.theme.TextDark
import com.blessed.blessblend.ui.theme.brown1
import com.blessed.blessblend.ui.theme.peach


// ---------------- FAVORITES SCREEN ----------------

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    navController: NavController,
    viewModel: FavoritesViewModel = viewModel()
) {

    var selectedIndex by remember { mutableStateOf(1) }

    Scaffold(

        topBar = {
            TopAppBar(
                title = { Text("Favorites") },

                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },

                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Default.Info,
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

            NavigationBar(containerColor = peach) {

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
                    }
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
                    }
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
                    }
                )
            }
        }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .paint(
                    painter = painterResource(R.drawable.img),
                    contentScale = ContentScale.FillBounds
                )
                .padding(24.dp)
        ) {

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                items(viewModel.savedImages) { product ->

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
}

@Preview(showBackground = true)
@Composable
fun FavoritesScreenPreview() {
    FavoritesScreen(rememberNavController())
}