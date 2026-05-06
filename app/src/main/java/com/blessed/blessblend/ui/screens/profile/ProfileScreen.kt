package com.blessed.blessblend.ui.screens.profile


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import com.blessed.blessblend.ui.theme.BackgroundCream
import com.blessed.blessblend.ui.theme.DividerGray
import com.blessed.blessblend.ui.theme.GoldDeep
import com.blessed.blessblend.ui.theme.GoldPearl
import com.blessed.blessblend.ui.theme.TextDark
import com.blessed.blessblend.ui.theme.TextGray
import com.blessed.blessblend.ui.theme.brown
import com.blessed.blessblend.ui.theme.brown1


@Composable
fun ProfileScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(painter = painterResource(R.drawable.img), contentScale = ContentScale.FillBounds)
            .padding(24.dp)
    ) {
        // 1. Header Section with Overlapping Image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
        ) {



            // Profile Picture (Circular & Overlapping)
            Image(
                painter = painterResource(id = R.drawable.profile), // Add to res/drawable
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(110.dp)
                    .align(Alignment.BottomCenter)
                    .clip(CircleShape)
                    .border(4.dp, Color.White, CircleShape),
                contentScale = ContentScale.Crop
            )
        }

        // 2. User Info
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Kelly Jones",
                fontSize = 26.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.SemiBold,
                color = TextDark
            )
            Text(
                text = "kellyportman@gmail.com",
                fontSize = 16.sp,
                color = brown
            )
            Spacer(modifier = Modifier.height(32.dp))
        }





    }


}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview(){
    ProfileScreen(rememberNavController())

}

