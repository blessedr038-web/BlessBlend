package com.blessed.blessblend.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.blessed.blessblend.R

@Composable
fun HomeScreen(navController: NavController) {
    // The Box acts as the root container to keep the background image static
    Box(
        modifier = Modifier
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
            // Top Row: Section 1 (Fair) & Section 2 (Medium)
            Row(modifier = Modifier.weight(1f)) {
                SkinToneSection(
                    modifier = Modifier.weight(1f),
                    imageRes = R.drawable.face1
                ) {
                    navController.navigate("fair_tone_screen")
                }

                SkinToneSection(
                    modifier = Modifier.weight(1f),
                    imageRes = R.drawable.face2
                ) {
                    navController.navigate("medium_tone_screen")
                }
            }

            // Bottom Row: Section 3 (Tan) & Section 4 (Deep)
            Row(modifier = Modifier.weight(1f)) {
                SkinToneSection(
                    modifier = Modifier.weight(1f),
                    imageRes = R.drawable.face3
                ) {
                    navController.navigate("tan_tone_screen")
                }

                SkinToneSection(
                    modifier = Modifier.weight(1f),
                    imageRes = R.drawable.face4
                ) {
                    navController.navigate("deep_tone_screen")
                }
            }
        }
    }
}

/**
 * Reusable component for each skin tone quadrant.
 * containerColor is set to Transparent to ensure the main background shows through.
 */
@Composable
fun SkinToneSection(modifier: Modifier, imageRes: Int, onClick: () -> Unit) {
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