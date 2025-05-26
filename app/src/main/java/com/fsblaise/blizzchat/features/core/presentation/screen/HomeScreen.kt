package com.fsblaise.blizzchat.features.core.presentation.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.ChatBubble
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.fsblaise.blizzchat.navigation.Chats
import com.fsblaise.blizzchat.navigation.Settings
import com.fsblaise.blizzchat.navigation.Stories

@Composable
fun HomeScreen(navController: NavController, content: @Composable (modifier: Modifier) -> Unit) {

    val tabs = remember {
        listOf(
            BottomNavItem<Chats>(
                title = "Chats",
                route = Chats,
                selectedIcon = Icons.Filled.ChatBubble,
                unselectedIcon = Icons.Outlined.ChatBubbleOutline
            ),
            BottomNavItem<Stories>(
                title = "Stories",
                route = Stories,
                selectedIcon = Icons.Filled.Image,
                unselectedIcon = Icons.Outlined.Image
            ),
            BottomNavItem<Settings>(
                title = "Settings",
                route = Settings,
                selectedIcon = Icons.Filled.Settings,
                unselectedIcon = Icons.Outlined.Settings
            )
        )
    }

    Scaffold(
        content = { padding ->
            content(Modifier.padding(padding))
        },
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            NavigationBar {
                tabs.forEach { tab ->
                    val isSelected = currentDestination?.hierarchy?.any {
                        it.route == tab.route::class.qualifiedName
                    } == true
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = if (isSelected) {
                                    tab.selectedIcon
                                } else {
                                    tab.unselectedIcon
                                },
                                contentDescription = null
                            )
                        },
                        label = { Text(tab.title) },
                        selected = isSelected,
                        onClick = {
                            navController.navigate(tab.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    )
}