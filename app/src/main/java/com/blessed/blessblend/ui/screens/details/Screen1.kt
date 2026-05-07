package com.blessed.blessblend.ui.screens.details

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
import com.blessed.blessblend.navigation.*
import com.blessed.blessblend.ui.theme.brown
import com.blessed.blessblend.ui.theme.brown1
import com.blessed.blessblend.ui.theme.peach

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen1(navController: NavController) {

    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(

        // ================= TOP BAR =================
        topBar = {
            TopAppBar(
                title = { Text("Fair Skin Guide", fontWeight = FontWeight.Bold) },
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

        // ================= BOTTOM BAR =================
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

        // ================= SCREEN CONTENT =================
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .paint(
                    painter = painterResource(R.drawable.img),
                    contentScale = ContentScale.FillBounds
                )
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {



            Text(
                text = "Fair Skin Tone",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Luminous Minimalism",
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4E342E),
                modifier = Modifier.padding(bottom = 24.dp)
            )

            InfoSection(
                title = "Recommended Palette",
                description = "Focus on Cool Pinks & Champagne. Use soft pastels, taupes, and rosy tones to enhance your natural glow without overpowering it."
            )

            InfoSection(
                title = "The Guidance",
                description = "Since fair skin shows pigment easily, try cream-based blushes for a natural flush. For eyes, champagne shimmers and soft mauves are your best friends."
            )

            InfoSection(
                title = "Digital Vanity Tip",
                description = "Save your favorite soft-glam looks to your Digital Vanity to keep track of styles that suit you perfectly."
            )

            Spacer(modifier = Modifier.height(40.dp))

            // --- UPDATED BUTTON ---
            Button(
                onClick = {
                    navController.navigate(ROUTE_PRODUCT1)

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5D4037)
                )
            ) {
                Text(
                    text = "Application",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White // Changed from 'brown' to 'Color.White' for better visibility on a dark button
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun InfoSection(title: String, description: String) {
    Column(modifier = Modifier.padding(vertical = 12.dp)) {

        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF4E342E)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = description,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            color = Color.DarkGray,
            fontWeight = FontWeight.Bold

        )
    }
}

@Preview(showBackground = true)
@Composable
fun Screen1Preview() {
    Screen1(rememberNavController())
}