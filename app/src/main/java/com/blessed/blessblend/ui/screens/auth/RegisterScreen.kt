package com.blessed.blessblend.ui.screens.auth

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.blessed.blessblend.R
import com.blessed.blessblend.navigation.ROUTE_LOGIN
import com.blessed.blessblend.ui.theme.PrimaryBrown
import com.blessed.blessblend.ui.theme.TextDark
import com.blessed.blessblend.ui.theme.TextGray
import com.blessed.blessblend.ui.theme.brown
import com.blessed.blessblend.ui.theme.brown1

// 🔥 Firebase imports
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

@Composable
fun RegisterScreen(navController: NavController) {

    val context = LocalContext.current

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val auth = FirebaseAuth.getInstance()
    val database = FirebaseDatabase.getInstance().reference

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(horizontal = 24.dp)
        ) {

            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 24.dp)
                    .clickable { navController.popBackStack() },
                tint = TextDark
            )

            Text(
                text = "Sign up",
                fontSize = 34.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                color = TextDark,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            CustomTextField(firstName, { firstName = it }, "First name")
            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(lastName, { lastName = it }, "Last name")
            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(email, { email = it }, "Email")
            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(password, { password = it }, "Password", true)

            Spacer(modifier = Modifier.height(48.dp))

            Button(
                onClick = {

                    // 🔒 Basic validation
                    if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                        Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    if (password.length < 6) {
                        Toast.makeText(context, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    // 🔥 Firebase Auth - Create User
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->

                            if (task.isSuccessful) {

                                val userId = auth.currentUser?.uid

                                // 📦 User data model
                                val userMap = HashMap<String, String>()
                                userMap["firstName"] = firstName
                                userMap["lastName"] = lastName
                                userMap["email"] = email
                                userMap["uid"] = userId ?: ""

                                // 🔥 Save to Realtime Database
                                database.child("Users").child(userId!!)
                                    .setValue(userMap)
                                    .addOnCompleteListener {

                                        Toast.makeText(context, "Account created successfully", Toast.LENGTH_SHORT).show()

                                        // Navigate to login
                                        navController.navigate(ROUTE_LOGIN) {
                                            popUpTo(0)
                                        }
                                    }

                            } else {
                                Toast.makeText(
                                    context,
                                    task.exception?.message ?: "Registration failed",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBrown)
            ) {
                Text("Register", color = brown, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "By signing up, you agreed to our Terms of Use and Privacy Policy",
                fontSize = 12.sp,
                color = TextDark,
                textAlign = TextAlign.Center,
                lineHeight = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        }
    }
}

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
        label = { Text(label, color = TextDark.copy(alpha = 0.7f)) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else androidx.compose.ui.text.input.VisualTransformation.None,
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