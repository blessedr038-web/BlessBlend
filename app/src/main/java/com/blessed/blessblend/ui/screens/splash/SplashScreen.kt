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
import com.blessed.blessblend.navigation.ROUTE_ONBOARDING
import com.blessed.blessblend.models.FavoritesManager
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController){

    // 🚀 Runs once when screen opens
    LaunchedEffect(Unit) {

        // ⏳ Optional small delay (keeps splash visible)
        delay(2000)

        // 🔥 LOAD FAVORITES FROM FIREBASE BEFORE ENTERING APP
        FavoritesManager.loadFromFirebase {

            // 🚀 Navigate after sync completes
            navController.navigate(ROUTE_ONBOARDING) {
                popUpTo(0) // clears splash from backstack
            }
        }
    }

    // 🔒 UI unchanged (NO MODIFICATIONS)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.logo),
                contentScale = ContentScale.FillBounds
            )
            .padding(24.dp)
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview(){
    SplashScreen(rememberNavController())
}