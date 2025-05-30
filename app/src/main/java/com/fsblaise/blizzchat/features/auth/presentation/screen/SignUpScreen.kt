package com.fsblaise.blizzchat.features.auth.presentation.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.fsblaise.blizzchat.features.auth.presentation.components.PageIndicator
import com.fsblaise.blizzchat.features.auth.presentation.components.SignUpAuth
import com.fsblaise.blizzchat.features.auth.presentation.components.SignUpName
import com.fsblaise.blizzchat.features.auth.presentation.state.AuthViewModel
import com.fsblaise.blizzchat.features.auth.presentation.state.SignUpFormViewModel
import com.fsblaise.blizzchat.navigation.Auth
import com.fsblaise.blizzchat.navigation.Home
import com.fsblaise.blizzchat.theme.BlizzChatTheme

@Composable
fun SignUpScreen(
    navController: NavController,
    formViewModel: SignUpFormViewModel = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val formState = formViewModel.state.value
    val scope = rememberCoroutineScope()
    var currentStep by remember { mutableIntStateOf(0) }

    fun switchSteps() {
        currentStep = if (currentStep == 1) {
            0
        } else {
            1
        }
    }

    fun signUp() {
        authViewModel.signIn(formState.email, formState.password)
        // Handle sign-up logic here, e.g., validate inputs and navigate to home screen.
        // This is a placeholder for the actual sign-up logic.
        navController.navigate(Home) {
            popUpTo(Auth) { inclusive = true }
        }
    }

    Scaffold(content = { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            if (currentStep == 1) {
                IconButton(onClick = { switchSteps() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.weight(1f)
            ) {
                AnimatedContent(
                    targetState = currentStep,
                    transitionSpec = {
                        if (currentStep == 1) {
                            slideInHorizontally { width -> width } + fadeIn() togetherWith
                                    slideOutHorizontally { width -> -width } + fadeOut()
                        } else {
                            slideInHorizontally { width -> -width } + fadeIn() togetherWith
                                    slideOutHorizontally { width -> width } + fadeOut()
                        }.using(
                            SizeTransform(clip = false)
                        )
                    },
                    label = "animated content",
                    modifier = Modifier.weight(1f)
                ) { step ->
                    when (step) {
                        0 -> SignUpAuth(
                            navController = navController,
                            viewModel = formViewModel,
                            state = formState,
                            onClick = { switchSteps() },
                        )
                        1 -> SignUpName(
                            viewModel = formViewModel,
                            state = formState,
                            onClick = { signUp() },
                        )
                    }
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                PageIndicator(index = 0, currentIndex = currentStep)
                Spacer(modifier = Modifier.size(8.dp))
                PageIndicator(index = 1, currentIndex = currentStep)
            }
        }
    })
}

@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    val mockNavController = rememberNavController()
    BlizzChatTheme {
        SignUpScreen(mockNavController)
    }
}