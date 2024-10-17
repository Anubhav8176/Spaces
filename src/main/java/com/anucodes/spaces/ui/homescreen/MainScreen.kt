package com.anucodes.spaces.ui.homescreen

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.anucodes.spaces.authentication.viewmodel.AuthViewmodel
import com.anucodes.spaces.chatfunction.viewmodel.ChatViewmodel
import com.anucodes.spaces.navigation.BottomNavigationBar


@Composable
fun MainScreen(
    navController: NavHostController,
    authViewmodel: AuthViewmodel,
    chatViewmodel: ChatViewmodel
){

    val homeNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(homeNavController)
        }
    ){innerpading->
        NavHost(
            navController = homeNavController,
            startDestination = "bottom_home"
        ) {
            composable("bottom_home"){
                HomeScreen(chatViewmodel, authViewmodel, navController, innerpading)
            }

            composable("add_chat"){
                AddFriend(chatViewmodel = chatViewmodel)
            }

            composable("profiles"){
                ProfileScreen(authViewmodel, navController)
            }
        }
    }
}