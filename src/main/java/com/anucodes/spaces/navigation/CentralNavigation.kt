package com.anucodes.spaces.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.anucodes.spaces.authentication.viewmodel.AuthViewmodel
import com.anucodes.spaces.chatfunction.viewmodel.ChatViewmodel
import com.anucodes.spaces.ui.authentication.LoginScreen
import com.anucodes.spaces.ui.authentication.SignUpScreen
import com.anucodes.spaces.ui.homescreen.MainScreen


@Composable
fun CentralNavigation(
    authViewmodel: AuthViewmodel,
    chatViewmodel: ChatViewmodel,
    navController: NavHostController
){

    val isLoggedIn by authViewmodel.isLoggedIn.collectAsState()

    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn){
            "home_graph"
        }else{
            "auth_graph"
        }
    ) {
        navigation(
            startDestination = "login_screen",
            route = "auth_graph"
        ){

            composable("login_screen"){
                LoginScreen(
                    authViewmodel,
                    navController
                )
            }

            composable("signup_screen"){
                SignUpScreen(
                    authViewmodel,
                    navController
                )
            }

        }

        navigation(
            startDestination = "home",
            route = "home_graph"
        ){
            composable("home"){
                MainScreen( navController, authViewmodel)
            }
        }
    }

}