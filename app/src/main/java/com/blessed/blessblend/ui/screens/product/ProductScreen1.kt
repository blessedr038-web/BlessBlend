package com.blessed.blessblend.ui.screens.product

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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
import com.blessed.blessblend.navigation.ROUTE_FAVORITES
import com.blessed.blessblend.navigation.ROUTE_FINALE1
import com.blessed.blessblend.navigation.ROUTE_HOME
import com.blessed.blessblend.navigation.ROUTE_PROFILE
import com.blessed.blessblend.ui.theme.brown1
import com.blessed.blessblend.ui.theme.peach

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen1(navController: NavController) {
    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Fair Skin", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Info, contentDescription = "Info")
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
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            // --- TOP SECTION: 80% Image ---
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.8f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.p1),
                    contentDescription = "Makeup Guide",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            // --- BOTTOM SECTION: 20% Info Area ---
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.2f)
                    .background(peach)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "Application Guide",
                    fontSize = 20.sp,
                    color = brown1,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Reference the facial map above for ideal product placement.",
                    color = Color.DarkGray,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                // --- FIXED BUTTON ---
                Button(
                    onClick = {
                        navController.navigate(ROUTE_FINALE1)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = brown1)
                ) {
                    Text(text = "Final Look", color = Color.White)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductScreen1Preview() {
    ProductScreen1(rememberNavController())
}