package com.fsblaise.blizzchat.features.settings.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.fsblaise.blizzchat.features.auth.presentation.state.AuthViewModel
import com.fsblaise.blizzchat.theme.BlizzChatTheme

@Composable
fun SettingsScreen(
    navController: NavController,
    authViewModel: AuthViewModel,
) {
    SettingsScreen(
        navController = navController
    )
}

@Composable
fun SettingsScreen(
    navController: NavController,
) {
    Scaffold(content = { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Settings Page",
                fontSize = 32.sp
            )
        }
    })
}

@Preview(showBackground = true)
@Composable
fun SettingsPreview() {
    val mockNavController = rememberNavController()
    BlizzChatTheme {
        SettingsScreen(mockNavController)
    }
}