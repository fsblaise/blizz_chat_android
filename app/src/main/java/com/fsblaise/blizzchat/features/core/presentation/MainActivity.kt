package com.fsblaise.blizzchat.features.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fsblaise.blizzchat.features.auth.presentation.state.AuthViewModel
import com.fsblaise.blizzchat.features.stories.domain.model.Story
import com.fsblaise.blizzchat.features.stories.domain.utils.StoryNavType
import com.fsblaise.blizzchat.navigation.Auth
import com.fsblaise.blizzchat.navigation.Camera
import com.fsblaise.blizzchat.navigation.DisplayPicture
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

                println("state is: $authState")
                NavHost(navController = navController, startDestination = Auth) {
                    authNavGraph(navController = navController)
                    homeNavGraph(navController = navController)
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
            }
        }
    }
}