package com.anucodes.spaces.chatfunction.viewmodel

import com.anucodes.spaces.authentication.model.NewUser

data class Friends(
    val user: NewUser? = null,
    val lastMessage: String? = ""
)
