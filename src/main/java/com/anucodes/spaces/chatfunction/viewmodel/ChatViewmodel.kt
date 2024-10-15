package com.anucodes.spaces.chatfunction.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewmodel @Inject constructor(
    private val db: FirebaseDatabase
): ViewModel() {


}