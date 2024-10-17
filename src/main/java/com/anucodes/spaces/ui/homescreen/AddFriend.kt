package com.anucodes.spaces.ui.homescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.anucodes.spaces.chatfunction.viewmodel.ChatViewmodel

@Composable
fun AddFriend(
    chatViewmodel: ChatViewmodel
){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        
        Text(
            text = "This is Add new Friends Screen!!"
        )
    }
}