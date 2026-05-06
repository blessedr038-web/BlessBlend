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
import com.blessed.blessblend.navigation.ROUTE_PRODUCT3
import com.blessed.blessblend.ui.theme.brown


@Composable
fun Screen3(navController: NavController) {
    // Keeping the editorial Cream background
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
            text = "Tan Skin Tone",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "High-Pigment Vibrancy",
            fontSize =21.sp,
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

        // --- BUTTON ADDED HERE ---
        Button(
            onClick = {
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
fun TanInfoSection(title: String, description: String) {
    Column(modifier = Modifier.padding(vertical = 12.dp)) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color =  Color(0xFF4E342E) // Using the Earthy Brown shade for the headers
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
fun Screen3Preview() {
    Screen3(rememberNavController())
}