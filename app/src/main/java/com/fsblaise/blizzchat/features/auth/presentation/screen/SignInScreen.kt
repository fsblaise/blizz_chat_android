package com.fsblaise.blizzchat.features.auth.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.fsblaise.blizzchat.features.auth.presentation.state.SignInFormViewModel
import com.fsblaise.blizzchat.navigation.Home
import com.fsblaise.blizzchat.navigation.SignUp
import com.fsblaise.blizzchat.theme.BlizzChatTheme

@Composable
fun SignInScreen(
    navController: NavController,
    viewModel: SignInFormViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    val scope = rememberCoroutineScope()

    Scaffold(content = { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome back",
                fontSize = 38.sp,
                fontWeight = FontWeight.Bold
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Please sign in to your account",
                    fontSize = 18.sp,
                )
                Spacer(Modifier.height(16.dp))
                OutlinedTextField(
                    value = state.email,
                    onValueChange = { viewModel.onEmailChange(it) },
                    label = { Text("Email") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = state.password,
                    onValueChange = { viewModel.onPasswordChange(it) },
                    label = { Text("Password") },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
                Button(
                    onClick = {
                        navController.navigate(Home)
                    },
                    enabled = state.isValid,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Sign in")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Not a member yet?",
                    )
                    TextButton(onClick = {
                        navController.navigate(SignUp)
                    }) {
                        Text(text = "Sign up")
                    }
                }
            }
        }
    })
}

@Preview(showBackground = true)
@Composable
fun SignInPreview() {
    val mockNavController = rememberNavController()
    BlizzChatTheme {
        SignInScreen(mockNavController)
    }
}