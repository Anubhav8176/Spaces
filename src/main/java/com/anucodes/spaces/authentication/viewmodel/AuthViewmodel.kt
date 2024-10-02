package com.anucodes.spaces.authentication.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anucodes.spaces.authentication.authstate.AuthState
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewmodel @Inject constructor(
    private val auth: FirebaseAuth
): ViewModel() {

    private var _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    private val authState = _authState.asStateFlow()


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
        email: String,
        password: String
    ){
         viewModelScope.launch {
             _authState.value = AuthState.Loading
             auth.createUserWithEmailAndPassword(email, password)
                 .addOnSuccessListener {

                 }
                 .addOnFailureListener{

                 }
         }
    }
}