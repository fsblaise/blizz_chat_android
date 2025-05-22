package com.fsblaise.blizzchat.features.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fsblaise.blizzchat.features.auth.presentation.screen.SignIn
import com.fsblaise.blizzchat.features.auth.presentation.screen.SignInScreen
import com.fsblaise.blizzchat.features.auth.presentation.screen.SignUp
import com.fsblaise.blizzchat.features.auth.presentation.screen.SignUpScreen
import com.fsblaise.blizzchat.features.core.presentation.screen.Home
import com.fsblaise.blizzchat.features.core.presentation.screen.HomeScreen
import com.fsblaise.blizzchat.features.core.presentation.screen.Welcome
import com.fsblaise.blizzchat.features.core.presentation.screen.WelcomeScreen
import com.fsblaise.blizzchat.theme.BlizzChatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BlizzChatTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Welcome) {
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
                    composable<Home> {
                        HomeScreen(navController = navController)
                    }
                }
            }
        }
    }
}