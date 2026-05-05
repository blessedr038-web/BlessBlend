package com.blessed.blessblend.ui.screens.onboarding

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

import com.blessed.blessblend.R
import com.blessed.blessblend.navigation.ROUTE_LOGIN
import com.blessed.blessblend.ui.theme.*

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.navigation.compose.rememberNavController
import com.blessed.blessblend.navigation.ROUTE_HOME
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(
    navController: NavController,
    onEmailClick: () -> Unit,
    onGoogleClick: () -> Unit = {}
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val auth = FirebaseAuth.getInstance()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        Image(
            painter = painterResource(R.drawable.img1),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Log in",
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(top = 16.dp),
                textAlign = TextAlign.End,
                color = brown1,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Discover ",
                color = brown,
                fontSize = 50.sp,
                letterSpacing = 0.5.sp
            )

            Box(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .width(1.dp)
                    .height(40.dp)
                    .background(TextSecondary.copy(alpha = 0.5f))
            )

            Text(
                text = "The Best\nBeauty Trends",
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Serif,
                fontSize = 38.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 44.sp,
                color = TextMain
            )

            Spacer(modifier = Modifier.height(48.dp))

            // 🔥 GOOGLE SIGN-IN BUTTON (CONNECTED)
            Button(
                onClick = {
                    scope.launch {
                        signInWithGoogle(
                            context = context,
                            onSuccess = {
                                navController.navigate(ROUTE_HOME) {
                                    popUpTo(0)
                                }
                            },
                            onError = {
                                it.printStackTrace()
                            }
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = RoundedCornerShape(28.dp),
                border = BorderStroke(1.dp, Color.LightGray)
            ) {
                Text(
                    text = "Continue with Google",
                    color = TextMain,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    onEmailClick()
                    navController.navigate(ROUTE_LOGIN)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ButtonDark),
                shape = RoundedCornerShape(28.dp)
            ) {
                Text(
                    text = "Continue with email",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}

/**
 * 🔐 GOOGLE SIGN-IN FUNCTION (Firebase)
 */
suspend fun signInWithGoogle(
    context: Context,
    onSuccess: () -> Unit,
    onError: (Exception) -> Unit
) {
    try {
        val credentialManager = CredentialManager.create(context)

        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId("425714815292-ccfc10ep6eoo7auh298l6pncqm720mqu.apps.googleusercontent.com") // 🔥 replace this
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        val result = credentialManager.getCredential(context, request)

        val credential = result.credential
        val idToken = credential.data.getString("id_token")

        if (idToken != null) {
            val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
            FirebaseAuth.getInstance()
                .signInWithCredential(firebaseCredential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        onSuccess()
                    } else {
                        onError(Exception("Firebase auth failed"))
                    }
                }
        } else {
            onError(Exception("No ID token found"))
        }

    } catch (e: Exception) {
        onError(e)
    }
}
@Preview(showBackground = true)
@Composable fun OnboardingScreenPreview() {
OnboardingScreen( navController = rememberNavController(), onEmailClick = {}, onGoogleClick = {} ) }
