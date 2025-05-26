package com.fsblaise.blizzchat.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.fsblaise.blizzchat.features.chats.presentation.screen.ChatsScreen
import com.fsblaise.blizzchat.features.core.presentation.screen.HomeScreen
import com.fsblaise.blizzchat.features.settings.presentation.screen.SettingsScreen
import com.fsblaise.blizzchat.features.stories.presentation.screen.StoriesScreen

fun NavGraphBuilder.homeNavGraph(navController: NavController) {
    navigation<Home> (
        startDestination = Chats,
    ) {
        composable<Chats> {
            HomeScreen(navController) {
                ChatsScreen(navController)
            }
        }
        composable<Settings> {
            HomeScreen(navController) {
                SettingsScreen(navController)
            }
        }
        composable<Stories>() {
            HomeScreen(navController) {
                StoriesScreen(navController)
            }
        }
    }
}