package com.fsblaise.blizzchat.features.auth.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.fsblaise.blizzchat.features.auth.presentation.state.SignUpFormState
import com.fsblaise.blizzchat.features.auth.presentation.state.SignUpFormViewModel

@Composable
fun SignUpName(
    viewModel: SignUpFormViewModel,
    state: SignUpFormState,
    onClick: () -> Unit
) {
    val colorScheme = colorScheme
    // This function will contain the UI for the full name input field.
    // It will include a TextField for the full name and display any error messages.
    // The implementation details are omitted for brevity.
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxHeight()
    ) {
        Text(
            text = "What's your name?",
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            lineHeight = 38.sp
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            OutlinedTextField(
                value = state.fullName,
                onValueChange = { viewModel.onFullNameChange(it) },
                label = { Text("Full Name") },
                singleLine = true,
                isError = state.fullNameError != null,
                supportingText = if (state.fullNameError != null) {
                    { Text(text = state.fullNameError, color = colorScheme.error) }
                } else null,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = onClick,
                enabled = state.isValid,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Sign Up")
            }
        }
    }
}

@Preview
@Composable
fun SignUpNamePreview() {
    val state = SignUpFormState(
        fullName = "John Doe",
        fullNameError = null,
        email = "",
        emailError = null,
        password = "",
        passwordError = null,
        rePassword = "",
        rePasswordError = null,
        isStepOneValid = true,
        isValid = true,
    )

    Scaffold(
        content = { padding ->

            Box(
                modifier = Modifier
                    .padding(padding)
                    .padding(24.dp)
            ) {
                SignUpName(
                    viewModel = hiltViewModel(),
                    state = state,
                    onClick = {},
                )
            }
        })
}