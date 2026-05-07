package com.blessed.blessblend.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.blessed.blessblend.R
import com.blessed.blessblend.navigation.ROUTE_FAVORITES
import com.blessed.blessblend.navigation.ROUTE_HOME
import com.blessed.blessblend.navigation.ROUTE_PROFILE
import com.blessed.blessblend.navigation.ROUTE_SCREEN1
import com.blessed.blessblend.navigation.ROUTE_SCREEN2
import com.blessed.blessblend.navigation.ROUTE_SCREEN3
import com.blessed.blessblend.navigation.ROUTE_SCREEN4
import com.blessed.blessblend.ui.theme.brown1
import com.blessed.blessblend.ui.theme.peach

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    // ✅ Default to HOME selected
    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(

        // 🔝 Top Bar
        topBar = {
            TopAppBar(
                title = { Text("Home", fontWeight = FontWeight.Bold) },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = peach,
                    titleContentColor = brown1,
                    navigationIconContentColor = brown1,
                    actionIconContentColor = brown1
                ),
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Info, contentDescription = "")
                    }
                }
            )
        },

        // 🔻 Bottom Bar (UPDATED)
        bottomBar = {
            NavigationBar(containerColor = peach) {

                // HOME ICON WITH BACKGROUND
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

                // PROFILE ICON WITHOUT BACKGROUND
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

                // FAVORITES ICON WITHOUT WHITE BACKGROUND
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
        }

    ) { paddingValues ->

        // 🔥 ORIGINAL UI (UNCHANGED)
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .paint(
                    painter = painterResource(R.drawable.img),
                    contentScale = ContentScale.FillBounds
                )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {

                Row(modifier = Modifier.weight(1f)) {

                    SkinToneSection(
                        modifier = Modifier.weight(1f),
                        imageRes = R.drawable.face1
                    ) {
                        navController.navigate(ROUTE_SCREEN1)
                    }

                    SkinToneSection(
                        modifier = Modifier.weight(1f),
                        imageRes = R.drawable.face2
                    ) {
                        navController.navigate(ROUTE_SCREEN2)
                    }
                }

                Row(modifier = Modifier.weight(1f)) {

                    SkinToneSection(
                        modifier = Modifier.weight(1f),
                        imageRes = R.drawable.face3
                    ) {
                        navController.navigate(ROUTE_SCREEN3)
                    }

                    SkinToneSection(
                        modifier = Modifier.weight(1f),
                        imageRes = R.drawable.face4
                    ) {
                        navController.navigate(ROUTE_SCREEN4)
                    }
                }
            }
        }
    }
}

// 🔁 Reusable Section (UNCHANGED)
@Composable
fun SkinToneSection(
    modifier: Modifier,
    imageRes: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxHeight()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Skin Tone Option",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}