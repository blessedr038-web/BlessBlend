package com.blessed.blessblend.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.blessed.blessblend.navigation.ROUTE_LOGIN
import com.blessed.blessblend.ui.theme.PrimaryBrown
import com.blessed.blessblend.ui.theme.TextDark
import com.blessed.blessblend.ui.theme.TextGray
import com.blessed.blessblend.ui.theme.brown1

@Composable
fun RegisterScreen(navController: NavController) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Use a Box to layer the background and content
    Box(modifier = Modifier.fillMaxSize()) {
        // 1. New Background Image (From image_7.png)
        Image(
            // Replace 'img_background' with the actual drawable file name
            painter = painterResource(id = com.blessed.blessblend.R.drawable.img),
            contentDescription = null,
            contentScale = ContentScale.Crop, // Fill the screen without distortion
            modifier = Modifier.fillMaxSize()
        )

        // 2. The Content Column (Layered on top)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding() // Add padding for the status bar
                .padding(horizontal = 24.dp)
        ) {
            // Functional Back Arrow
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 24.dp)
                    .clickable { navController.popBackStack() }, // Navigates back
                tint = TextDark
            )

            // Serif Title to match editorial branding
            Text(
                text = "Sign up",
                fontSize = 34.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                color = TextDark,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Input Fields (With fixed logic and styling)
            CustomTextField(value = firstName, onValueChange = { firstName = it }, label = "First name")
            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(value = lastName, onValueChange = { lastName = it }, label = "Last name")
            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(value = email, onValueChange = { email = it }, label = "Email")
            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(value = password, onValueChange = { password = it }, label = "Password", isPassword = true)

            Spacer(modifier = Modifier.height(48.dp))

            // Create Account Button
            Button(
                onClick = { navController.navigate(ROUTE_LOGIN) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBrown)
            ) {
                Text("Create account", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Terms text
            Text(
                text = "By signing up, you agreed to our Terms of Use and Privacy Policy",
                fontSize = 12.sp,
                color = brown1,
                textAlign = TextAlign.Center,
                lineHeight = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        }
    }
}

// Minimalist text field for a cleaner look against the complex background
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isPassword: Boolean = false
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = brown1.copy(alpha = 0.7f)) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = PrimaryBrown,
            unfocusedIndicatorColor = TextGray.copy(alpha = 0.5f),
            cursorColor = PrimaryBrown
        )
    )
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(rememberNavController())
}