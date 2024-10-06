package com.anucodes.spaces.ui.homescreen

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.anucodes.spaces.authentication.viewmodel.AuthViewmodel
import com.anucodes.spaces.navigation.BottomNavigationBar


@Composable
fun MainScreen(
    navController: NavHostController,
    authViewmodel: AuthViewmodel
){

    val homeNavController = rememberNavController()

    LaunchedEffect(Unit){
        authViewmodel.fetchCurrentUser()
    }

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
                HomeScreen(authViewmodel, navController, innerpading)
            }

            composable("add_chat"){

            }

            composable("profiles"){
                ProfileScreen(authViewmodel, navController)
            }
        }
    }
}