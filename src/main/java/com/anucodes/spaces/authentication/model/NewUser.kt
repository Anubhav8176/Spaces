package com.anucodes.spaces.authentication.model

import com.anucodes.spaces.chatfunction.viewmodel.Friends

data class NewUser(
    val name: String? = "",
    val username: String? = "",
    val email: String? = "",
    val profilePicture: String? = "",
    val selectedDate: String? = "",
    val friends: List<Friends> = emptyList()
)
