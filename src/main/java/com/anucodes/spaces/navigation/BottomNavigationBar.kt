package com.anucodes.spaces.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.util.fastCbrt
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(
    navController: NavHostController
){
    val items = listOf(
        BottomNavigationItems.Home,
        BottomNavigationItems.AddChat,
        BottomNavigationItems.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    NavigationBar {
        items.forEach {item->
            NavigationBarItem(
                label = {
                    Text(
                        text = item.label
                    )
                },
                selected = currentDestination == item.route,
                onClick = {
                    navController.navigate(item.route){
                        popUpTo(item.route){inclusive=true}
                    }
                },
                alwaysShowLabel = false,
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label
                    )
                }
            )
        }
    }
}
