package com.blessed.blessblend.ui.screens.auth


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.blessed.blessblend.R
import com.blessed.blessblend.navigation.ROUTE_HOME
import com.blessed.blessblend.navigation.ROUTE_VERIFYEMAIL
import com.blessed.blessblend.ui.theme.BorderGray
import com.blessed.blessblend.ui.theme.TextDark
import com.blessed.blessblend.ui.theme.TextGray
import com.blessed.blessblend.ui.theme.brown1

@Composable
fun VerifyEmailScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(painter = painterResource(R.drawable.img), contentScale = ContentScale.FillBounds)
            .padding(24.dp)
    ) {
        // Back Arrow
        IconButton(onClick = { navController.popBackStack()}, modifier = Modifier.offset(x = (-12).dp)) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = TextDark
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Title
        Text(
            text = "Verify Email",
            fontSize = 32.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.SemiBold,
            color = TextDark
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Subtitle
        Text(
            text = "We have sent you an email with a code to blessedr038@gmail.com",
            fontSize = 14.sp,
            color = brown1,
            lineHeight = 20.sp
        )

        Spacer(modifier = Modifier.height(56.dp))

        // OTP Input Circles
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OtpCircle(value = "8", isFocused = true)
            OtpCircle(value = "-", isFocused = false)
            OtpCircle(value = "-", isFocused = false)
            OtpCircle(value = "-", isFocused = false)
        }

        Spacer(modifier = Modifier.height(64.dp))

        // Resend Code Link
        TextButton(
            onClick = { /* Handle resend */ },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                "Send me a new code",
                color = TextDark,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )


        }
        TextButton(
            onClick = {navController.navigate(ROUTE_HOME) },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                "Enter",
                color = TextDark,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )


        }
    }
}

@Preview(showBackground = true)
@Composable
fun VerifyEmailScreenPreview(){
    VerifyEmailScreen(rememberNavController())

}

@Composable
fun OtpCircle(value: String, isFocused: Boolean) {
    Box(
        modifier = Modifier
            .size(64.dp)
            .border(
                width = 1.dp,
                color = if (isFocused)TextDark else BorderGray,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = value,
            fontSize = 24.sp,
            fontFamily = FontFamily.Serif,
            color = if (value == "-")BorderGray else TextDark,
            textAlign = TextAlign.Center
        )
    }
}