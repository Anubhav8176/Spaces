package com.anucodes.spaces.ui.homescreen

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
import androidx.compose.material3.Card
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
                searchText = it
            },
            shape = RoundedCornerShape(15.dp)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(0.95f)
        ){

            items(10){
                SearchedTitle()
            }
//            items(searchedfriends){
//                SearchedTitle(it)
//            }
        }
        Spacer(modifier = Modifier.weight(20f))
    }
}

@Composable
fun SearchedTitle(
//    friends: NewUser
){
    Card(
        modifier = Modifier.fillMaxWidth()
    ){
        Text(
            modifier = Modifier.padding(10.dp),
            text = "Name of User",
            fontSize = 16.sp,
            fontFamily = poppinsFam
        )
        Text(
            modifier = Modifier.padding(10.dp),
            text = "Username of User",
            fontSize = 16.sp,
            fontFamily = poppinsFam
        )
    }
}