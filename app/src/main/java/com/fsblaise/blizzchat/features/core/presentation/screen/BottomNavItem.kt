package com.fsblaise.blizzchat.features.core.presentation.screen

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem<T>(
    val title: String,
    val route: T,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)
