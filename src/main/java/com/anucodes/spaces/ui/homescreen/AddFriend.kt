package com.anucodes.spaces.ui.homescreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anucodes.spaces.authentication.model.NewUser
import com.anucodes.spaces.chatfunction.viewmodel.ChatViewmodel
import com.anucodes.spaces.chatfunction.viewmodel.Friends
import com.anucodes.spaces.ui.theme.poppinsFam

@Composable
fun AddFriend(
    chatViewmodel: ChatViewmodel,
    innerpadding: PaddingValues
){

    var searchText by remember { mutableStateOf("") }
    val searchedfriends by chatViewmodel.searchedFriends.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerpadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){

        Spacer(modifier = Modifier.weight(2f))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(0.95f),
            value = searchText,
            onValueChange = {
                chatViewmodel.searchNewFriend(searchText)
                searchText = it
            },
            shape = RoundedCornerShape(15.dp)
        )
        Spacer(modifier = Modifier.weight(1f))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(0.95f)
        ){
            items(searchedfriends){
                SearchedTitle(it, chatViewmodel)
            }
        }
        Spacer(modifier = Modifier.weight(20f))
    }
}

@Composable
fun SearchedTitle(
    friends: NewUser,
    chatViewmodel: ChatViewmodel
){
    Card(
        modifier = Modifier.fillMaxWidth(),
        border = BorderStroke(1.dp, Color.Black),
        elevation = CardDefaults.elevatedCardElevation(10.dp)
    ){
        Column(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            friends.name?.let {
                Text(
                    modifier = Modifier
                        .padding(10.dp),
                    text = it,
                    fontSize = 16.sp,
                    fontFamily = poppinsFam,
                    color = Color.Black
                )
            }
            friends.username?.let {
                Text(
                    modifier = Modifier
                        .padding(10.dp),
                    text = it,
                    fontSize = 16.sp,
                    fontFamily = poppinsFam,
                    color = Color.Black
                )
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth(0.8f),
                onClick = {
                    val newFriend = Friends(friends, "")
                    chatViewmodel.addNewFriend(newFriend)
                },
                border = BorderStroke(1.dp, Color.Black),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.DarkGray
                ),
                elevation = ButtonDefaults.elevatedButtonElevation(10.dp)
            ) {
                Text(
                    text = "Add the Friend!!",
                    fontSize = 16.sp,
                    fontFamily = poppinsFam,
                )
            }
        }
    }
}