package com.fsblaise.blizzchat.features.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
import kotlin.reflect.typeOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BlizzChatTheme {
                val navController = rememberNavController()
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