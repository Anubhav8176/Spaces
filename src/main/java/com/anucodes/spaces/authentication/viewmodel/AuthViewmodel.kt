package com.anucodes.spaces.authentication.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anucodes.spaces.authentication.authstate.AuthState
import com.anucodes.spaces.authentication.model.NewUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.rpc.context.AttributeContext.Auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewmodel @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
): ViewModel() {

    private var _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState = _authState.asStateFlow()

    private var _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn = _isLoggedIn.asStateFlow()


    fun LoginUser(
        email: String,
        password: String
    ){
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    _authState.value = AuthState.Success
                    Log.i("Authentication: ", "The User is LoggedIn Successfully")
                }
                .addOnFailureListener{
                    _authState.value = AuthState.Failure(it.message.toString())
                    Log.i("Authentication: ", "The User is not LoggedIn")
                }
        }
    }


    fun RegisterUser(
        name: String,
        username: String,
        email: String,
        password: String
    ){
         viewModelScope.launch {
             _authState.value = AuthState.Loading
             auth.createUserWithEmailAndPassword(email, password)
                 .addOnSuccessListener {
                     val currUser = auth.currentUser
                     if (currUser!=null){
                         val userId = currUser.uid

                         val newUser = NewUser(
                             name = name,
                             username = username,
                             email = email,
                             profilePicture = ""
                         )
                         firestore.collection("user")
                             .document(userId)
                             .set(newUser)
                             .addOnSuccessListener {
                                 _authState.value = AuthState.Success
                                 Log.i("Register User: ", "The user is registered successfully in firebase")
                             }
                             .addOnFailureListener {
                                 _authState.value = AuthState.Failure(it.message.toString())
                                 Log.i("Register User: ", "The user is not registered to firebase ${it.message}")
                             }
                     }

                 }
                 .addOnFailureListener{
                     Log.i("Auth Registration: ", "The user failed to created in authentication.")
                 }
         }
    }
}