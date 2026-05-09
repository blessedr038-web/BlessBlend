package com.blessed.blessblend.ui.screens.splash

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.blessed.blessblend.R
import com.blessed.blessblend.models.FavoritesManager
import com.blessed.blessblend.navigation.ROUTE_ONBOARDING
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController){

    // 🚀 Runs once when screen opens
    LaunchedEffect(Unit) {
        // ⏳ Keep splash visible for 2 seconds
        delay(2000)

        // 🔥 Sync backend data
        FavoritesManager.loadFromFirebase() {
            // 🚀 Navigate ONLY after Firebase responds
            navController.navigate(ROUTE_ONBOARDING) {
                // This clears the Splash screen from history so back button doesn't go back to it
                popUpTo(navController.graph.startDestinationId) {
                    inclusive = true
                }
            }
        }
    }

    // 🔒 UI (Kept exactly as requested)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.logo),
                contentScale = ContentScale.FillBounds
            )
            .padding(24.dp)
    ) {
        // UI content remains empty as per your original structure
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview(){
    SplashScreen(rememberNavController())
}