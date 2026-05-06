package com.blessed.blessblend.ui.screens.auth
// 🔥 Add imports
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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
import com.blessed.blessblend.navigation.ROUTE_FORGOTPASSWORD
import com.blessed.blessblend.navigation.ROUTE_VERIFYEMAIL
import com.blessed.blessblend.ui.theme.BackgroundCream
import com.blessed.blessblend.ui.theme.PrimaryBrown
import com.blessed.blessblend.ui.theme.SubtitleGray
import com.blessed.blessblend.ui.theme.TextDark
import com.blessed.blessblend.ui.theme.TextGray
import com.blessed.blessblend.ui.theme.brown
import com.blessed.blessblend.ui.theme.brown1

@Composable
fun ForgotPasswordScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }

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
            text = "Forgot Password?",
            fontSize = 32.sp,
            fontFamily = FontFamily.Serif, // Matching the serif style from onboarding
            fontWeight = FontWeight.SemiBold,
            color = TextDark
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Instruction Text
        Text(
            text = "Enter your email to receive the instruction to reset your password",
            fontSize = 14.sp,
            color = TextDark,
            lineHeight = 25.sp
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Email Input (Underline style)
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Your Email", color = TextDark, fontSize = 18.sp) },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = TextDark,
                unfocusedIndicatorColor = TextGray.copy(alpha = 0.5f),
                cursorColor = TextDark
            )
        )

        Spacer(modifier = Modifier.height(64.dp))

        val context = LocalContext.current
        val auth = FirebaseAuth.getInstance()

        Button(
            onClick = {

                if (email.isEmpty()) {
                    Toast.makeText(context, "Enter your email", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                // 🔥 SEND RESET EMAIL (THIS WAS MISSING)
                auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->

                        if (task.isSuccessful) {

                            Toast.makeText(
                                context,
                                "Reset link sent to your email",
                                Toast.LENGTH_LONG
                            ).show()

                            // ✅ PASS EMAIL PROPERLY
                            navController.navigate(ROUTE_VERIFYEMAIL)

                        } else {
                            Toast.makeText(
                                context,
                                task.exception?.message ?: "Failed to send email",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(percent = 50),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryBrown)
        ) {
            Text("Send me now", color = brown, fontSize = 16.sp)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ForgotPasswordPreview(){
    ForgotPasswordScreen(rememberNavController())

}
