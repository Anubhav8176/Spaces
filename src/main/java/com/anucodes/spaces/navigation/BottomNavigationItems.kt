package com.anucodes.spaces.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationItems(
    val route: String,
    val icon: ImageVector,
    val label: String
) {
    object Home: BottomNavigationItems("bottom_home", Icons.Outlined.Home, "Chats")
    object AddChat: BottomNavigationItems("add_chat", Icons.Outlined.Add, "Add Chat")
    object Profile: BottomNavigationItems("profiles", Icons.Outlined.Person, "Profile")
}