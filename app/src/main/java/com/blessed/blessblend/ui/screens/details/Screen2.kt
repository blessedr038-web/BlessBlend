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
import com.blessed.blessblend.navigation.ROUTE_PRODUCT2
import com.blessed.blessblend.ui.theme.brown


@Composable
fun Screen2(navController: NavController) {
    // Consistent Cream background for the editorial look
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
            text = "Medium Skin Tone",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Golden Hour Glow",
            fontSize =21.sp,
            color = Color(0xFF4E342E),
            modifier = Modifier.padding(bottom = 24.dp)
        )

        MediumInfoSection(
            title = "Recommended Palette",
            description = "Play up your natural warmth with Gold & Warm Mauves. Rich bronzes, copper, and olive greens are perfect for eyes, while coral or mauve lips provide a stunning finish."
        )

        MediumInfoSection(
            title = "The Guidance",
            description = "Embrace highlighters with gold flecks on the cheekbones and bridge of the nose. Warm, earthy browns help define your features without looking muddy."
        )

        MediumInfoSection(
            title = "Idea Tracker Tip",
            description = "Found a perfect bronzing tutorial or a copper eye look? Pin it to your trackers to keep your beauty inspiration organized in one place."
        )

        Spacer(modifier = Modifier.height(40.dp))


        // --- BUTTON ADDED HERE ---
        Button(
            onClick = {
                navController.navigate(ROUTE_PRODUCT2)
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
fun MediumInfoSection(title: String, description: String) {
    Column(modifier = Modifier.padding(vertical = 12.dp)) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color =  Color(0xFF4E342E) // Using a muted terracotta shade for the headings
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
fun Screen2Preview() {
    Screen2(rememberNavController())
}