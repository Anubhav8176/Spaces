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

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState = _authState.asStateFlow()

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn = _isLoggedIn.asStateFlow()

    private val _currentUser = MutableStateFlow<NewUser?>(null)
    val currentUser = _currentUser.asStateFlow()

    init {
        checkIfUserExist()
    }

    private fun checkIfUserExist(){
        val user = auth.currentUser
        if (user != null){
            Log.i("The user: ", "The User is found")
            fetchCurrentUser()
            _isLoggedIn.value = true
        }else{
            Log.i("The user: ", "The User is not found")
            fetchCurrentUser()
            _isLoggedIn.value = false
        }
    }

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
        password: String,
        selectedDate: String
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
                             profilePicture = "",
                             selectedDate = selectedDate,
                             friends = emptyList()
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
                     Log.i("Auth Registration: ", "The user failed to created in authentication.$it")
                 }
         }
    }

    fun logout(){
        auth.signOut()
        _authState.value = AuthState.Idle
    }

    fun updateAuthState(){
        _authState.value = AuthState.Idle
    }

    fun fetchCurrentUser(){
        val currUser = auth.currentUser
        if (currUser != null){
            val userId = currUser.uid

            firestore.collection("user")
                .document(userId)
                .get()
                .addOnSuccessListener {
                    if (it.exists()){
                        val user = NewUser(
                            it.getString("name"),
                            it.getString("username"),
                            it.getString("email"),
                            it.getString("profilePicture"),
                            it.getString("selectedDate"),
                        )
                        Log.i("The User is: ", "The User Info is: $user")
                        _currentUser.value = user
                    }else{
                        logout()
                    }
                }
                .addOnFailureListener {
                    Log.i("The User is: ", "The User is signing out")
                    logout()
                }
        }else{
            Log.i("The User: ", "The User is found null")
        }
    }
}