package com.fsblaise.blizzchat.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

fun NavGraphBuilder.homeNavGraph(navController: NavController) {
    navigation<Home> (
        startDestination = Chats,
    ) {
        composable<Chats> {

        }
        composable<Settings> {

        }
        composable<Stories>() {

        }
    }
}