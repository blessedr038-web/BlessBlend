package com.blessed.blessblend.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.blessed.blessblend.ui.screens.auth.ForgotPasswordScreen
import com.blessed.blessblend.ui.screens.auth.LoginScreen
import com.blessed.blessblend.ui.screens.auth.RegisterScreen
import com.blessed.blessblend.ui.screens.auth.VerifyEmailScreen
import com.blessed.blessblend.ui.screens.details.Screen1
import com.blessed.blessblend.ui.screens.details.Screen2
import com.blessed.blessblend.ui.screens.details.Screen3
import com.blessed.blessblend.ui.screens.details.Screen4
import com.blessed.blessblend.ui.screens.home.HomeScreen
import com.blessed.blessblend.ui.screens.onboarding.OnboardingScreen
import com.blessed.blessblend.ui.screens.profile.ProfileScreen
import com.blessed.blessblend.ui.screens.search.SearchScreen
import com.blessed.blessblend.ui.screens.splash.SplashScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_SPLASH
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUTE_SPLASH) {
            SplashScreen(navController)
        }
        composable(ROUTE_PROFILE) {
            ProfileScreen(navController)
        }
        composable(ROUTE_HOME) {
            HomeScreen(navController)
        }
        composable(ROUTE_SEARCH) {
            SearchScreen(navController)
        }

        composable(ROUTE_REGISTER) {
            RegisterScreen(navController)
        }

        composable(ROUTE_LOGIN) {
            LoginScreen(navController)
        }


        composable(ROUTE_FORGOTPASSWORD) {
            ForgotPasswordScreen(navController)
        }

        composable(ROUTE_VERIFYEMAIL) {
            VerifyEmailScreen(navController)
        }

        composable(ROUTE_ONBOARDING){
            OnboardingScreen( navController = navController,
                onEmailClick = {
                    navController.navigate(ROUTE_REGISTER) // Use your actual route name here
                },
                onGoogleClick = {
                    // Handle Google Sign-In logic or navigate
                    navController.navigate(ROUTE_REGISTER)
                })
        }
        composable(ROUTE_SCREEN1) {
            Screen1(navController)
        }
        composable(ROUTE_SCREEN2) {
            Screen2(navController)
        }
        composable(ROUTE_SCREEN3) {
            Screen3(navController)
        }
        composable(ROUTE_SCREEN4) {
            Screen4(navController)
        }









        }

































    }

