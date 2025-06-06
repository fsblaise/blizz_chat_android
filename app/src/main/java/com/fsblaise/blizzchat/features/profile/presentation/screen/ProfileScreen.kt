package com.fsblaise.blizzchat.features.profile.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ProfileScreen(
    navController: NavController
) {
    Scaffold(
        content = { padding ->
            Box(
                modifier = Modifier
                    .padding(padding)
                    .padding(24.dp),
                content = {
                    Text(text = "Profile Screen")
                }
            )
        }
    )
}