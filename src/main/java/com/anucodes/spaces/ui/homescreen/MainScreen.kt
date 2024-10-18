package com.anucodes.spaces.ui.homescreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.anucodes.spaces.authentication.viewmodel.AuthViewmodel
import com.anucodes.spaces.chatfunction.viewmodel.ChatViewmodel
import com.anucodes.spaces.navigation.BottomNavigationBar
import com.anucodes.spaces.ui.theme.poppinsFam


@OptIn(ExperimentalMaterial3Api::class)
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
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "Spaces",
                        fontSize = 40.sp,
                        fontFamily = poppinsFam,
                        textAlign = TextAlign.Center
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.DarkGray,
                    titleContentColor = Color.White
                )
            )
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
                AddFriend(chatViewmodel = chatViewmodel, innerpading)
            }

            composable("profiles"){
                ProfileScreen(authViewmodel, navController, innerpading)
            }
        }
    }
}