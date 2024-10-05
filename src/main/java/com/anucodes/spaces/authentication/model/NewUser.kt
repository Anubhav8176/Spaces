package com.anucodes.spaces.authentication.model

data class NewUser(
    val name: String,
    val username: String,
    val email: String,
    val profilePicture: String,
    val selectedDate: String
)
