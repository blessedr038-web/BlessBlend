package com.blessed.blessblend.ui.screens.auth

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.blessed.blessblend.R
import com.blessed.blessblend.navigation.ROUTE_FORGOTPASSWORD
import com.blessed.blessblend.navigation.ROUTE_HOME
import com.blessed.blessblend.ui.theme.PrimaryBrown
import com.blessed.blessblend.ui.theme.TextDark
import com.blessed.blessblend.ui.theme.TextGray
import com.blessed.blessblend.ui.theme.brown1

// 🔥 Firebase
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(navController: NavController) {

    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(painter = painterResource(R.drawable.img), contentScale = ContentScale.FillBounds)
            .padding(24.dp)
    ) {

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

        Text(
            text = "Log in",
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextDark,
            modifier = Modifier.padding(bottom = 40.dp)
        )

        LoginTextField(
            value = email,
            onValueChange = { email = it },
            label = "Your Email"
        )

        Spacer(modifier = Modifier.height(24.dp))

        LoginTextField(
            value = password,
            onValueChange = { password = it },
            label = "Password",
            isPassword = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Visibility,
                    contentDescription = "Toggle Password",
                    tint = TextDark
                )
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Switch(
                    checked = rememberMe,
                    onCheckedChange = { rememberMe = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedTrackColor = TextDark,
                        uncheckedThumbColor = Color.White,
                        uncheckedTrackColor = TextGray
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Remember", color = TextDark, fontSize = 14.sp)
            }

            TextButton(onClick = {
                navController.navigate(ROUTE_FORGOTPASSWORD)
            }) {
                Text(
                    "Forgot password?",
                    color = brown1,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = {

                // 🔒 Validation
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                // 🔥 Firebase Login
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->

                        if (task.isSuccessful) {

                            Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()

                            navController.navigate(ROUTE_HOME) {
                                popUpTo(0)
                            }

                        } else {
                            Toast.makeText(
                                context,
                                task.exception?.message ?: "Login failed",
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
            Text("Log in", color = Color.White, fontSize = 16.sp)
        }
    }
}

@Composable
fun LoginTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isPassword: Boolean = false,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = brown1, fontSize = 18.sp) },
        modifier = Modifier.fillMaxWidth(),
        trailingIcon = trailingIcon,
        singleLine = true,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else androidx.compose.ui.text.input.VisualTransformation.None,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            focusedIndicatorColor = TextDark,
            unfocusedIndicatorColor = TextGray,
            cursorColor = TextDark
        )
    )
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview(){
    LoginScreen(rememberNavController())
}