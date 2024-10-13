package com.anucodes.spaces

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.anucodes.spaces.authentication.viewmodel.AuthViewmodel
import com.anucodes.spaces.chatfunction.viewmodel.ChatViewmodel
import com.anucodes.spaces.navigation.CentralNavigation
import com.anucodes.spaces.ui.authentication.LoginScreen
import com.anucodes.spaces.ui.authentication.SignUpScreen
import com.anucodes.spaces.ui.theme.SpacesTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val authViewmodel by viewModels<AuthViewmodel>()
    private val chatViewmodel by viewModels<ChatViewmodel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()

            SpacesTheme {
                CentralNavigation(
                    authViewmodel = authViewmodel,
                    chatViewmodel = chatViewmodel,
                    navController = navController
                )
            }
        }
    }
}
