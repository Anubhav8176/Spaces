package com.anucodes.spaces.ui.homescreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.anucodes.spaces.R
import com.anucodes.spaces.authentication.viewmodel.AuthViewmodel
import com.anucodes.spaces.ui.theme.poppinsFam
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProfileScreen(
    authViewmodel: AuthViewmodel,
    navController: NavHostController,
    innerpadding: PaddingValues
){

    val currentUser by authViewmodel.currentUser.collectAsState()
    LaunchedEffect(currentUser){
        authViewmodel.fetchCurrentUser()
    }

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(innerpadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Spacer(modifier = Modifier.weight(4f))

        GlideImage(
            modifier = Modifier
                .height(300.dp)
                .aspectRatio(1f, matchHeightConstraintsFirst = true)
                .border(
                    1.dp,
                    Color.DarkGray,
                    shape = CircleShape
                )
                .padding(1.dp)
                .clip(CircleShape),
            model = currentUser?.profilePicture,
            contentDescription = "",
            failure = placeholder(painter = painterResource(R.drawable.avatar))
        )
        Spacer(modifier = Modifier.weight(1f))

        Text(
            modifier = Modifier
                .fillMaxWidth(0.85f),
            text = "Name",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = poppinsFam
        )
        Card(
            elevation = CardDefaults.elevatedCardElevation(10.dp),
            border = BorderStroke(1.dp, Color.Black)
        ){
            currentUser?.name?.let {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(10.dp),
                    text = it,
                    fontSize = 16.sp,
                    fontFamily = poppinsFam
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            modifier = Modifier
                .fillMaxWidth(0.85f),
            text = "Username",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = poppinsFam
        )
        Card(
            elevation = CardDefaults.elevatedCardElevation(10.dp),
            border = BorderStroke(1.dp, Color.Black)
        ){
            currentUser?.username?.let {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(10.dp),
                    text = it,
                    fontSize = 16.sp,
                    fontFamily = poppinsFam
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            modifier = Modifier
                .fillMaxWidth(0.85f),
            text = "Email",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = poppinsFam
        )
        Card(
            elevation = CardDefaults.elevatedCardElevation(10.dp),
            border = BorderStroke(1.dp, Color.Black)
        ){
            currentUser?.email?.let {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(10.dp),
                    text = it,
                    fontSize = 16.sp,
                    fontFamily = poppinsFam
                )
            }
        }

        Spacer(modifier = Modifier.weight(2f))

        Button(
            modifier = Modifier
                .fillMaxWidth(0.8f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            border = BorderStroke(1.dp, Color.Black),
            elevation = ButtonDefaults.elevatedButtonElevation(10.dp),
            onClick = {
                authViewmodel.logout()
                navController.navigate("auth_graph"){
                    popUpTo(navController.graph.startDestinationId){
                        inclusive = true
                    }
                    launchSingleTop=true
                }
            }
        ) {
            Text(
                text = "Logout!!",
                fontSize = 17.sp,
                fontFamily = poppinsFam,
                color = Color.DarkGray
            )
        }

        Spacer(modifier = Modifier.weight(10f))
    }
}