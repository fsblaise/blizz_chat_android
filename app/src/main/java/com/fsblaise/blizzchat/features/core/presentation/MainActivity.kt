package com.fsblaise.blizzchat.features.core.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fsblaise.blizzchat.features.auth.presentation.state.AuthState
import com.fsblaise.blizzchat.features.auth.presentation.state.AuthViewModel
import com.fsblaise.blizzchat.features.core.presentation.state.AuthStateWatcher
import com.fsblaise.blizzchat.features.stories.domain.model.Story
import com.fsblaise.blizzchat.features.stories.domain.utils.StoryNavType
import com.fsblaise.blizzchat.navigation.Auth
import com.fsblaise.blizzchat.navigation.Camera
import com.fsblaise.blizzchat.navigation.DisplayPicture
import com.fsblaise.blizzchat.navigation.Home
import com.fsblaise.blizzchat.navigation.Messaging
import com.fsblaise.blizzchat.navigation.ViewStory
import com.fsblaise.blizzchat.navigation.authNavGraph
import com.fsblaise.blizzchat.navigation.homeNavGraph
import com.fsblaise.blizzchat.theme.BlizzChatTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlin.reflect.typeOf

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BlizzChatTheme {
                val navController = rememberNavController()
                val authViewModel: AuthViewModel = hiltViewModel()
                val authState by authViewModel.state.collectAsState()
                val colorScheme = colorScheme
                val context = LocalContext.current

                NavHost(navController = navController, startDestination = Auth) {
                    authNavGraph(navController = navController, authViewModel = authViewModel)
                    homeNavGraph(navController = navController, authViewModel = authViewModel)
                    composable<Messaging> {
                        // add messaging screen here
                    }
                    composable<Camera> {
                        // add camera screen here
                    }
                    composable<DisplayPicture> {
                        // add display picture screen here
                    }
                    composable<ViewStory>(
                        typeMap = mapOf(
                            typeOf<Story>() to StoryNavType,
                        )
                    ) {
                        // add story screen here
                    }
                }
                AuthStateWatcher(
                    authState = authState,
                    navController = navController,
                    colorScheme = colorScheme,
                    context = context
                )
            }
        }
    }
}