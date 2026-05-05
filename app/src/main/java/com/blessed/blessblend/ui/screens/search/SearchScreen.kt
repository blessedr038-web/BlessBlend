package com.blessed.blessblend.ui.screens.search

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.blessed.blessblend.R
import com.blessed.blessblend.ui.theme.SearchBarBg
import com.blessed.blessblend.ui.theme.TextDark
import com.blessed.blessblend.ui.theme.TextGray


@Composable
fun SearchScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(painter = painterResource(R.drawable.background), contentScale = ContentScale.FillBounds)
    ) {
        // 1. Top Search Bar (Pill Shape)
        SearchBarSection()

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            // 2. Featured Trend Banner
            TrendBanner()

            Spacer(modifier = Modifier.height(32.dp))

            // 3. New Arrivals Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "New Arrivals",
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.SemiBold,
                    color = TextDark
                )
                Text(
                    text = "View all",
                    fontSize = 18.sp,
                    color = TextGray
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 4. Product Grid (Simplified for single scroll)
            Row(modifier = Modifier.fillMaxWidth()) {
                ProductItem(
                    name = "Highlight Palette",
                    brand = "Harly Babie",
                    price = "$50.00",
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(16.dp))
                ProductItem(
                    name = "Lip Gloss",
                    brand = "Cos Beauty",
                    price = "$20.00",
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(100.dp)) // Bottom Nav Clearance
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SearchScreenPreview(){
    SearchScreen(rememberNavController())

}
@Composable
fun SearchBarSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .height(50.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(SearchBarBg)
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Search", color =TextGray, modifier = Modifier.weight(1f))
            Icon(Icons.Default.Search, contentDescription = null, tint = TextGray)
        }
    }
}

@Composable
fun TrendBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(380.dp)
            .clip(RoundedCornerShape(24.dp))
    ) {
        Image(
            painter = painterResource(id = R.drawable.makeup), // Reference image
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Text(
            text = "Shop the\nbeauty trends",
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(24.dp),
            color = Color.White,
            fontSize = 36.sp,
            fontFamily = FontFamily.Serif,
            lineHeight = 42.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ProductItem(name: String, brand: String, price: String, modifier: Modifier) {
    Column(modifier = modifier) {
        Box(

            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.8f)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White),
            contentAlignment = Alignment.BottomEnd
        ) {
            // Product Image placeholder
            Image(
                painter = painterResource(id = R.drawable.palette), // Reference image
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Icon(
                Icons.Outlined.FavoriteBorder,
                contentDescription = null,
                tint = TextGray,
                modifier = Modifier.padding(12.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(brand, fontSize = 18.sp, color = TextGray)
        Text(name, fontSize = 18.sp, fontWeight = FontWeight.Medium, color = TextDark)
        Text(price, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = TextDark)
    }


}