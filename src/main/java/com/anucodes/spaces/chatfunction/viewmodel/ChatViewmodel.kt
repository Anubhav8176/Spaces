package com.anucodes.spaces.chatfunction.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anucodes.spaces.authentication.model.NewUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewmodel @Inject constructor(
    private val db: FirebaseDatabase,
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
): ViewModel() {

    private val _friends = MutableStateFlow<List<Friends>>(emptyList())
    val friends = _friends.asStateFlow()

    private val _searchedFriends = MutableStateFlow<List<NewUser>>(emptyList())
    val searchedFriends = _searchedFriends.asStateFlow()

    fun getAllFriends(){
        viewModelScope.launch {
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

    fun addNewFriend(friends: Friends){
        viewModelScope.launch {
            val user = auth.currentUser
            if (user != null){
                val userId = user.uid

                firestore.collection("user")
                    .document(userId)
                    .update("friends", friends)
                    .addOnSuccessListener {
                        getAllFriends()
                        Log.i("Friends: ", "New Friend added!!")
                    }
                    .addOnFailureListener {
                        Log.i("Friends: ", "No new friend is added because $it")
                    }
            }
        }
    }

    fun searchNewFriend(query: String){
        viewModelScope.launch {
            firestore.collection("user")
                .whereGreaterThan("username",query)
                .whereLessThan("username", query+"\uf8ff")
                .get()
                .addOnSuccessListener {
                    _searchedFriends.value = it.mapNotNull { it.toObject(NewUser::class.java) }
                    Log.i("The Search: ", "The search is successful.")
                }
                .addOnFailureListener {
                    Log.i("The Search: ", "The search is not successfull.")
                }
        }
    }

}