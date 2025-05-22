package com.fsblaise.blizzchat.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.fsblaise.blizzchat.features.auth.presentation.screen.SignInScreen
import com.fsblaise.blizzchat.features.auth.presentation.screen.SignUpScreen
import com.fsblaise.blizzchat.features.core.presentation.screen.WelcomeScreen


fun NavGraphBuilder.authNavGraph(navController: NavController) {
    navigation<Auth> (
        startDestination = Welcome,
    ) {
        composable<Welcome> {
            WelcomeScreen(navController = navController)
        }
        // Add the rest of the screens here
        composable<SignIn> {
            SignInScreen(navController = navController)
        }
        composable<SignUp> {
            SignUpScreen(navController = navController)
        }
    }
}