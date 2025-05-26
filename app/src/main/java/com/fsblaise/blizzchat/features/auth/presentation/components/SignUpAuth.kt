package com.fsblaise.blizzchat.features.auth.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.fsblaise.blizzchat.features.auth.presentation.state.SignUpFormState
import com.fsblaise.blizzchat.features.auth.presentation.state.SignUpFormViewModel
import com.fsblaise.blizzchat.navigation.SignUp

@Composable
fun SignUpAuth(
    navController: NavController,
    viewModel: SignUpFormViewModel,
    state: SignUpFormState,
    onClick: () -> Unit
) {
    val colorScheme = colorScheme
    // This function will contain the UI for the sign-up authentication process.
    // It will include the steps for entering email, password, re-entering password, and full name.
    // The implementation details are omitted for brevity.
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxHeight()
    ) {
        Text(
            text = "Welcome newcomer",
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            lineHeight = 38.sp
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Please sign up to create an account",
                fontSize = 18.sp,
            )
            Spacer(Modifier.height(16.dp))
            OutlinedTextField(
                value = state.email,
                onValueChange = { viewModel.onEmailChange(it) },
                label = { Text("Email") },
                singleLine = true,
                isError = state.emailError != null,
                supportingText = if (state.emailError != null) {
                    { Text(text = state.emailError, color = colorScheme.error) }
                } else null,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = state.password,
                onValueChange = { viewModel.onPasswordChange(it) },
                label = { Text("Password") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                isError = state.passwordError != null,
                supportingText = if (state.passwordError != null) {
                    { Text(text = state.passwordError, color = colorScheme.error) }
                } else null,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = state.rePassword,
                onValueChange = { viewModel.onRePasswordChange(it) },
                label = { Text("Re-enter Password") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                isError = state.rePasswordError != null,
                supportingText = if (state.rePasswordError != null) {
                    { Text(text = state.rePasswordError, color = colorScheme.error) }
                } else null,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = onClick,
                enabled = state.isStepOneValid,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Continue")
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Already a member?",
                )
                TextButton(onClick = {
                    navController.navigate(SignUp)
                }) {
                    Text(text = "Sign in")
                }
            }
        }
    }
}

@Preview
@Composable
fun SignUpAuthPreview() {
    val state = SignUpFormState(
        email = "test@gmail.com",
        emailError = null,
        password = "",
        passwordError = "Password is required",
        rePassword = "",
        rePasswordError = null,
        fullName = "",
        fullNameError = null,
        isStepOneValid = false,
        isValid = false,
    )
    val mockNavController = rememberNavController()

    Scaffold(
        content = { padding ->
            Box(
                modifier = Modifier
                    .padding(padding)
                    .padding(24.dp)
            ) {
                SignUpAuth(
                    navController = mockNavController,
                    viewModel = hiltViewModel(),
                    state = state,
                    onClick = {},
                )
            }
        }
    )
}