package com.fsblaise.blizzchat.features.core.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fsblaise.blizzchat.features.settings.presentation.screen.SettingsScreen
import com.fsblaise.blizzchat.navigation.Chats
import com.fsblaise.blizzchat.navigation.ProfileDialog
import com.fsblaise.blizzchat.navigation.Settings
import com.fsblaise.blizzchat.navigation.Stories

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    content: @Composable (modifier: Modifier) -> Unit,
) {

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

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val routeName = currentDestination?.route?.substringAfterLast('.') ?: "Placeholder"
    val colorScheme = colorScheme

    Scaffold(
        content = { padding ->
            content(Modifier.padding(padding))
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier.padding(start = 24.dp),
                        fontSize = 24.sp,
                        text = routeName
                    )
                },
                navigationIcon = {
                    // TODO: profile selector dialog
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .background(
                                color = colorScheme.primaryContainer,
                                shape = CircleShape
                            ).padding(4.dp)
                            .clickable(onClick = {
                                navController.navigate(ProfileDialog)
                            })
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Settings)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = "Settings",
                        )
                    }
                }
            )
        },
        bottomBar = {
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

@Preview
@Composable
fun HomeScreenPreview() {
    val mockNavController = rememberNavController()

    HomeScreen(
        navController = mockNavController,
    ) {
        SettingsScreen(mockNavController)
    }
}