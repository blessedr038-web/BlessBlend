package com.blessed.blessblend.ui.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.blessed.blessblend.navigation.ROUTE_PRODUCT4
import com.blessed.blessblend.ui.theme.brown


@Composable
fun Screen4(navController: NavController) {
    // Keeping the editorial Cream background for a unified app experience
    val creamBackground = Color(0xFFCBAE99)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(painter = painterResource(R.drawable.img), contentScale = ContentScale.FillBounds)
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {
        Spacer(modifier = Modifier.height(30.dp) )

        Text(
            text = "Deep Skin Tone",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Rich Saturation",
            fontSize = 21.sp,
            color = Color(0xFF4E342E),
            modifier = Modifier.padding(bottom = 24.dp)
        )

        DeepInfoSection(
            title = "Recommended Palette",
            description = "Embrace Opulent Darks and Royal Brights. High-shine molten golds, midnight blues, and royal purples look regal on your skin. For lips, explore vivid magentas, deep plums, and rich, true chocolates."
        )

        DeepInfoSection(
            title = "The Guidance",
            description = "Prioritize high-pigment, 'saturation-first' products to ensure colors pop. Using orange-toned color correctors helps maintain a bright and seamless finish, especially under the eyes."
        )

        DeepInfoSection(
            title = "Digital Vanity Tip",
            description = "Curate your 'Royal Essentials' list in your Digital Vanity. Tracking these high-intensity looks ensures you always have inspiration for a bold, sophisticated statement."
        )

        Spacer(modifier = Modifier.height(40.dp))

        // --- BUTTON ADDED HERE ---
        Button(
            onClick = {
                navController.navigate(ROUTE_PRODUCT4)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF5D4037)
            )
        ) {
            Text(
                text = "View Curated Products",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = brown
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun DeepInfoSection(title: String, description: String) {
    Column(modifier = Modifier.padding(vertical = 12.dp)) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF4E342E) // A darker, rich espresso brown for the headers
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = description,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            color = Color.DarkGray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Screen4Preview() {
    Screen4(rememberNavController())
}