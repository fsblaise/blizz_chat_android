package com.fsblaise.blizzchat.features.profile.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fsblaise.blizzchat.navigation.Profile

@Composable
fun ProfileDialogScreen(
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
            .clickable(onClick = { navController.popBackStack() })
    ) {
        Card(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(24.dp)
        ) {
            Box(
                modifier = Modifier
                    .padding(32.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f), // semi-transparent
                        shape = MaterialTheme.shapes.medium
                    )
            ) {
                // Dialog content
                Button(onClick = { navController.navigate(Profile) }) {
                    Text("nav")
                }
            }
        }
    }
}