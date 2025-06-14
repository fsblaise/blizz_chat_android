package com.fsblaise.blizzchat.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.fsblaise.blizzchat.features.auth.presentation.screen.SignInScreen
import com.fsblaise.blizzchat.features.auth.presentation.screen.SignUpScreen
import com.fsblaise.blizzchat.features.auth.presentation.state.AuthViewModel
import com.fsblaise.blizzchat.features.companies.presentation.screen.CheckEmailScreen
import com.fsblaise.blizzchat.features.core.presentation.screen.WelcomeScreen


fun NavGraphBuilder.authNavGraph(navController: NavController, authViewModel: AuthViewModel) {
    navigation<Auth> (
        startDestination = Welcome,
    ) {
        composable<Welcome> {
            WelcomeScreen(navController = navController)
        }
        composable<CheckEmail> {
            CheckEmailScreen(navController = navController)
        }
        composable<SignIn> {
            SignInScreen(navController = navController, authViewModel = authViewModel)
        }
        composable<SignUp> {
            SignUpScreen(navController = navController, authViewModel = authViewModel)
        }
    }
}