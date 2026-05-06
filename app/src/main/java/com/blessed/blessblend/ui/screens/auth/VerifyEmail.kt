package com.blessed.blessblend.ui.screens.auth

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
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
import com.blessed.blessblend.ui.theme.BorderGray
import com.blessed.blessblend.ui.theme.TextDark
import com.blessed.blessblend.ui.theme.brown
import com.google.firebase.auth.FirebaseAuth

@Composable
fun VerifyEmailScreen(navController: NavController) {

    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()

    val userEmail = navController.currentBackStackEntry
        ?.arguments?.getString("email") ?: ""
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.img),
                contentScale = ContentScale.FillBounds
            )
            .padding(24.dp)
    ) {

        // Back Arrow
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.offset(x = (-12).dp)
        ) {
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

        // Subtitle (dynamic email)
        Text(
            text = "We have sent you an email with a code to $userEmail",
            fontSize = 14.sp,
            color = TextDark,
            lineHeight = 20.sp
        )

        Spacer(modifier = Modifier.height(56.dp))

        // OTP UI (unchanged - visual only)
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

        // 🔁 Resend Email
        TextButton(
            onClick = {

                if (userEmail.isEmpty()) {
                    Toast.makeText(context, "No email found", Toast.LENGTH_SHORT).show()
                    return@TextButton
                }

                auth.sendPasswordResetEmail(userEmail)
                    .addOnCompleteListener { task ->

                        if (task.isSuccessful) {
                            Toast.makeText(
                                context,
                                "Reset email sent again",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                context,
                                task.exception?.message ?: "Failed to resend",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                "Send me a new code",
                color = TextDark,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }

        // ✅ Continue Button
        TextButton(
            onClick = {
                Toast.makeText(
                    context,
                    "Check your email to reset password",
                    Toast.LENGTH_LONG
                ).show()

                navController.navigate(ROUTE_HOME) {
                    popUpTo(0)
                }
            },
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

@Composable
fun OtpCircle(value: String, isFocused: Boolean) {
    Box(
        modifier = Modifier
            .size(64.dp)
            .border(
                width = 1.dp,
                color = if (isFocused) TextDark else BorderGray,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = value,
            fontSize = 24.sp,
            fontFamily = FontFamily.Serif,
            color = if (value == "-") BorderGray else TextDark,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun VerifyEmailScreenPreview(){
    VerifyEmailScreen(rememberNavController())
}