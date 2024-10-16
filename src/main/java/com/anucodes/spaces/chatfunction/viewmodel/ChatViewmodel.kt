package com.anucodes.spaces.chatfunction.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ChatViewmodel @Inject constructor(
    private val db: FirebaseDatabase,
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
): ViewModel() {

    private val _friends = MutableStateFlow<List<Friends>>(emptyList());
    val friends = _friends.asStateFlow()

    fun getAllFriends(){
        val user = auth.currentUser
        if (user != null) {
            val userId = user.uid

            firestore.collection("user")
                .document(userId)
                .get()
                .addOnSuccessListener {
                    val fetchedUser = it.get("friends") as List<Friends>
                    _friends.value = fetchedUser
                    Log.i("The User", fetchedUser.toString())
                }
                .addOnFailureListener {
                    Log.i("The User: ", "")
                }
        }
    }

}