package com.anucodes.spaces.ui.authentication

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Key
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anucodes.spaces.ui.theme.authFam
import com.anucodes.spaces.ui.theme.heading
import com.anucodes.spaces.ui.theme.poppinsFam


@Composable
fun LoginScreen(){

    var email by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color(0xFFAF64FF)
            )
    ){

        Column(
            modifier = Modifier
                .fillMaxHeight(0.25f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Text(
                text = "Spaces",
                fontSize = 40.sp,
                fontFamily = heading,
                color = Color.White
            )
        }

        Column(
            modifier = Modifier
                .fillMaxHeight(0.75f)
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
                )
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(modifier = Modifier.weight(0.4f))

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Log In",
                fontSize = 25.sp,
                fontFamily = authFam,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.weight(0.1f))
            Text(
                modifier = Modifier
                    .fillMaxWidth(0.85f),
                text = "Email",
                fontSize = 18.sp,
                fontFamily = poppinsFam
            )
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                modifier = Modifier
                    .fillMaxWidth(0.9f),
                shape = RoundedCornerShape(15.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Email,
                        contentDescription = "Email"
                    )
                },
                placeholder = {
                    Text(
                        text ="Enter your email",
                        fontSize = 15.sp,
                        fontFamily = poppinsFam
                    )
                }
            )

            Spacer(modifier = Modifier.weight(0.1f))
            Text(
                modifier = Modifier
                    .fillMaxWidth(0.85f),
                text = "Password",
                fontSize = 18.sp,
                fontFamily = poppinsFam
            )
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                modifier = Modifier
                    .fillMaxWidth(0.9f),
                shape = RoundedCornerShape(15.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Key,
                        contentDescription = "Email"
                    )
                },
                placeholder = {
                    Text(
                        text ="Enter your password",
                        fontSize = 15.sp,
                        fontFamily = poppinsFam
                    )
                }
            )

            Spacer(modifier = Modifier.weight(0.1f))

            Button(
                modifier = Modifier
                    .fillMaxWidth(0.88f),
                onClick = {

                },
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Black
                ),
                border = BorderStroke(1.dp, Color.Black)
            ) {
                Text(
                    text = "Login",
                    fontSize = 15.sp,
                    fontFamily = poppinsFam,
                )
            }

            Spacer(modifier = Modifier.weight(0.09f))

            Button(
                modifier = Modifier
                    .fillMaxWidth(0.88f),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Black
                ),
                border = BorderStroke(1.dp, Color.Black),
                onClick = {}
            ) {
                Text(
                    text = "New to spaces?",
                    fontSize = 15.sp,
                    fontFamily = poppinsFam
                )
            }

            Spacer(modifier = Modifier.weight(0.5f))
        }

    }
}