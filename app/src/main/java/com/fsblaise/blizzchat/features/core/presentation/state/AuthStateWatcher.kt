package com.fsblaise.blizzchat.features.core.presentation.state

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fsblaise.blizzchat.features.auth.presentation.state.AuthState
import com.fsblaise.blizzchat.navigation.Auth
import com.fsblaise.blizzchat.navigation.Home

@Composable
fun AuthStateWatcher(
    authState: AuthState,
    navController: NavController,
    colorScheme: ColorScheme,
    context: Context
) {
    println("state is: $authState")

    when (authState) {
        is AuthState.Fetching, AuthState.Initial -> {
            Box {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    color = colorScheme.primary
                )
            }
        }
        is AuthState.Authenticated -> {
            navController.navigate(Home) {
                popUpTo(Auth) { inclusive = true }
                launchSingleTop = true
            }
        }
        is AuthState.Unauthenticated -> {
            navController.navigate(Auth) {
                popUpTo(Home) { inclusive = true }
                launchSingleTop = true
            }
        }

        is AuthState.Error -> {
            Toast.makeText(
                context,
                authState.message,
                Toast.LENGTH_SHORT
            ).show()
        }
        AuthState.HelloSuccess -> {
            // Do nothing
        }
    }
}