package com.anucodes.spaces.ui.homescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.anucodes.spaces.authentication.viewmodel.AuthViewmodel
import com.anucodes.spaces.navigation.BottomNavigationBar
import com.anucodes.spaces.ui.theme.poppinsFam


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    authViewmodel: AuthViewmodel,
    navController: NavHostController,
    innerpadding: PaddingValues
){
    Column(modifier = Modifier.padding()){
        Scaffold(
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
        ){ innerpadding->
            Column(
                modifier = Modifier
                    .padding(innerpadding)
            ){
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    text = "Chats",
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = poppinsFam
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    items(100){
                        ChatCardInfo()
                    }
                }
            }
        }
    }
}


@Composable
fun ChatCardInfo(){
    Card(
        modifier = Modifier
            .padding(vertical = 7.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .padding(horizontal = 10.dp, vertical = 8.dp)
        ){
            Text(
                text = "Name of the user",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppinsFam
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "12:00",
                fontSize = 16.sp,
                fontFamily = poppinsFam
            )
        }
        Text(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(horizontal = 10.dp, vertical = 2.dp),
            text = "This is place where the last message by the particular sender will be visible",
            fontSize = 16.sp,
            color = Color.Gray,
            maxLines = 1
        )
    }
}