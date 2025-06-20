package com.fsblaise.blizzchat.features.core.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import com.fsblaise.blizzchat.R
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.fsblaise.blizzchat.navigation.CheckEmail
import com.fsblaise.blizzchat.navigation.SignIn
import com.fsblaise.blizzchat.theme.BlizzChatTheme

@Composable
fun WelcomeScreen(navController: NavController) {
    Scaffold(content = { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = 32.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                navController.navigate(CheckEmail)
            }) {
                Text(text = "Start")
            }
        }
    })
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val mockNavController = rememberNavController()
    BlizzChatTheme {
        WelcomeScreen(mockNavController)
    }
}