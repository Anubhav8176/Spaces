package com.anucodes.spaces.ui.homescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.anucodes.spaces.authentication.viewmodel.AuthViewmodel
import com.anucodes.spaces.navigation.BottomNavigationBar
import com.anucodes.spaces.ui.theme.poppinsFam


@Composable
fun HomeScreen(
    authViewmodel: AuthViewmodel,
    navController: NavHostController,
    innerpadding: PaddingValues
){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Text(
                text = "This is home Screen",
                fontSize = 30.sp,
                fontFamily = poppinsFam
            )
            Button(
                onClick = {
                    authViewmodel.logout()
                    navController.navigate("auth_graph"){
                        popUpTo(navController.graph.startDestinationId){
                            inclusive=true
                        }
                    }
                }
            ) {
                Text(
                    text = "Logout!",
                    fontSize = 16.sp,
                    fontFamily = poppinsFam
                )
            }
        }
}