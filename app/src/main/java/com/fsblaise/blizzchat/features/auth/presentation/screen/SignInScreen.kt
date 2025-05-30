package com.fsblaise.blizzchat.features.auth.presentation.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.fsblaise.blizzchat.BuildConfig
import com.fsblaise.blizzchat.features.auth.presentation.state.AuthState.Authenticated
import com.fsblaise.blizzchat.features.auth.presentation.state.AuthViewModel
import com.fsblaise.blizzchat.features.auth.presentation.state.SignInFormViewModel
import com.fsblaise.blizzchat.navigation.Auth
import com.fsblaise.blizzchat.navigation.Home
import com.fsblaise.blizzchat.navigation.SignUp
import com.fsblaise.blizzchat.theme.BlizzChatTheme

@Composable
fun SignInScreen(
    navController: NavController,
    formViewModel: SignInFormViewModel = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val formState = formViewModel.state.value
    val authState = authViewModel.state.collectAsState()
    val scope = rememberCoroutineScope()
    val colorScheme = colorScheme
    val context = LocalContext.current

    println(BuildConfig.API_URL)

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
                    value = formState.email,
                    onValueChange = { formViewModel.onEmailChange(it) },
                    label = { Text("Email") },
                    singleLine = true,
                    isError = formState.emailError != null,
                    supportingText = if (formState.emailError != null) {
                        { Text(text = formState.emailError, color = colorScheme.error) }
                    } else null,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = formState.password,
                    onValueChange = { formViewModel.onPasswordChange(it) },
                    label = { Text("Password") },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    isError = formState.passwordError != null,
                    supportingText = if (formState.passwordError != null) {
                        { Text(text = formState.passwordError, color = colorScheme.error) }
                    } else null,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
                Button(
                    onClick = {
                        authViewModel.signIn(formState.email, formState.password)
//                        navController.navigate(Home)
                    },
                    enabled = formState.isValid,
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
                if (authState.value is Authenticated) {
                    LaunchedEffect(Unit) {
                        Toast.makeText(
                            context,
                            "Sign in successful!",
                            Toast.LENGTH_SHORT
                        ).show()
                        // Optionally navigate to Home after toast
                        navController.navigate(Home) {
                            popUpTo(Auth) { inclusive = true }
                        }
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