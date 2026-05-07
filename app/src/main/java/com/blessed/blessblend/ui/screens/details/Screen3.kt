package com.blessed.blessblend.ui.screens.details

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
fun Screen3(navController: NavController) {

    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(

        // ================= TOP BAR =================
        topBar = {
            TopAppBar(
                title = { Text("Tan Skin Guide", fontWeight = FontWeight.Bold) },
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
                text = "Tan Skin Tone",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "High-Pigment Vibrancy",
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4E342E),
                modifier = Modifier.padding(bottom = 24.dp)
            )

            TanInfoSection(
                title = "Recommended Palette",
                description = "Your skin tone is the perfect canvas for Sunset Hues and Jewel Tones. Fiery oranges, metallic ambers, and deep teals make a striking statement. For lips, bold corals and brick reds are your go-to choices."
            )

            TanInfoSection(
                title = "The Guidance",
                description = "Don't be afraid of saturation! Tan skin carries bold pigments beautifully. Experiment with colorful eyeliners and warm-toned bronzers to further enhance your natural depth."
            )

            TanInfoSection(
                title = "Digital Vanity Tip",
                description = "Collect your boldest evening looks and high-contrast styles in your Digital Vanity. It's the best way to see which vibrant trends you love most."
            )

            Spacer(modifier = Modifier.height(40.dp))

            // --- UPDATED BUTTON ---
            Button(
                onClick = {
                    // Double check that ROUTE_PRODUCT1 in your Navigation graph
                    // actually points to ProductScreen1 and not the Finale.
                    navController.navigate(ROUTE_PRODUCT3)
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
fun TanInfoSection(title: String, description: String) {
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
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Screen3Preview() {
    Screen3(rememberNavController())
}