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

    // 🚀 High-speed navigation logic
    LaunchedEffect(Unit) {
        // 1. Start loading favorites (added {} to fix the compiler error)
        FavoritesManager.loadFromFirebase { }

        // 2. Hard timer: Navigate exactly after 2 seconds
        delay(2000)

        navController.navigate(ROUTE_ONBOARDING) {
            popUpTo(navController.graph.startDestinationId) {
                inclusive = true
            }
        }
    }

    // 🔒 UI (STRICTLY UNCHANGED)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.logo),
                contentScale = ContentScale.FillBounds
            )
            .padding(24.dp)
    ) { }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview(){
    SplashScreen(rememberNavController())
}