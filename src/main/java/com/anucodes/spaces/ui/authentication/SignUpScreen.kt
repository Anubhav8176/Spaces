package com.anucodes.spaces.ui.authentication

import android.widget.Toast
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Key
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.anucodes.spaces.authentication.authstate.AuthState
import com.anucodes.spaces.authentication.viewmodel.AuthViewmodel
import com.anucodes.spaces.ui.theme.authFam
import com.anucodes.spaces.ui.theme.heading
import com.anucodes.spaces.ui.theme.poppinsFam
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    authViewmodel: AuthViewmodel,
    navController: NavHostController
){

    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("") }
    var passwordVisi by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val authState by authViewmodel.authState.collectAsState()


    when(authState){
        is AuthState.Success->{
            navController.navigate("home_graph"){
                popUpTo(navController.graph.startDestinationId){
                    inclusive=true
                }
                launchSingleTop=true
            }
        }
        is AuthState.Failure->{
            Toast.makeText(context, (authState as AuthState.Failure).message.toString(), Toast.LENGTH_LONG).show()
            email = ""
            password = ""
            authViewmodel.updateAuthState()
        }
        is AuthState.Loading->{

        }
        is AuthState.Idle->{

        }
    }

    if (showDialog){
        DatePickerDialog(
            onDismissRequest = {
                showDialog = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        selectedDate = datePickerState.selectedDateMillis?.let {
                            SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date(it))
                        }.toString()
                        showDialog = false
                    }
                ) {
                    Text(
                        text = "Ok",
                        fontSize = 15.sp,
                        fontFamily = poppinsFam
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text(
                        text = "Cancel",
                        fontSize = 15.sp,
                        fontFamily = poppinsFam
                    )
                }
            }
        ){
            DatePicker(
                state = datePickerState,
                title = {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        text = "Select Date of birth",
                        fontSize = 15.sp,
                        fontFamily = poppinsFam,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color(0xFFAF64FF)
            )
    ){

        Column(
            modifier = Modifier
                .fillMaxHeight(0.2f)
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
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
                )
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Spacer(modifier = Modifier.weight(0.4f))

            Text(
                text = "Create a new account!",
                fontSize = 25.sp,
                fontFamily = authFam,
                modifier = Modifier
                    .fillMaxWidth(),
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.weight(0.1f))

            Text(
                modifier = Modifier
                    .fillMaxWidth(0.85f),
                text = "Name",
                fontSize = 15.sp,
                fontFamily = poppinsFam
            )
            Card(
                elevation = CardDefaults.elevatedCardElevation(7.dp)
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = {
                        name = it
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.9f),
                    shape = RoundedCornerShape(15.dp),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Person,
                            contentDescription = "Name"
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Enter fullname",
                            fontSize = 15.sp,
                            fontFamily = poppinsFam
                        )
                    }
                )
            }

            Spacer(modifier = Modifier.weight(0.1f))

            Text(
                modifier = Modifier
                    .fillMaxWidth(0.85f),
                text = "Username",
                fontSize = 15.sp,
                fontFamily = poppinsFam
            )
            Card(
                elevation = CardDefaults.elevatedCardElevation(7.dp)
            ) {
                OutlinedTextField(
                    value = username,
                    onValueChange = {
                        username = it
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.9f),
                    shape = RoundedCornerShape(15.dp),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Person,
                            contentDescription = "Username"
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Enter username",
                            fontSize = 15.sp,
                            fontFamily = poppinsFam
                        )
                    }
                )
            }

            Spacer(modifier = Modifier.weight(0.1f))

            Text(
                modifier = Modifier
                    .fillMaxWidth(0.85f),
                text = "Email",
                fontSize = 15.sp,
                fontFamily = poppinsFam
            )
            Card(
                elevation = CardDefaults.elevatedCardElevation(7.dp)
            ) {
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
                            text = "Enter email",
                            fontSize = 15.sp,
                            fontFamily = poppinsFam
                        )
                    }
                )
            }

            Spacer(modifier = Modifier.weight(0.1f))

            Text(
                modifier = Modifier
                    .fillMaxWidth(0.85f),
                text = "Password",
                fontSize = 15.sp,
                fontFamily = poppinsFam
            )
            Card(
                elevation = CardDefaults.elevatedCardElevation(7.dp)
            ) {
                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.9f),
                    shape = RoundedCornerShape(15.dp),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Key,
                            contentDescription = "Password"
                        )
                    },
                    trailingIcon = {
                        val visiIcon = if (passwordVisi) {
                            Icons.Outlined.Visibility
                        } else {
                            Icons.Outlined.VisibilityOff
                        }

                        IconButton(
                            onClick = { passwordVisi = !passwordVisi }
                        ) {
                            Icon(
                                imageVector = visiIcon,
                                contentDescription = "passwordVisibility"
                            )
                        }
                    },
                    visualTransformation = if (passwordVisi) VisualTransformation.None else PasswordVisualTransformation(),
                    placeholder = {
                        Text(
                            text = "Enter Password",
                            fontSize = 15.sp,
                            fontFamily = poppinsFam
                        )
                    }
                )
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth(0.88f),
                text = "Date of birth",
                fontSize = 16.sp,
                fontFamily = poppinsFam
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.9f),
                border = BorderStroke(1.dp, Color.Black),
                elevation = CardDefaults.elevatedCardElevation(10.dp)
            ){
                Row {
                    IconButton(
                        onClick = {
                            showDialog = true
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Add date"
                        )
                    }

                    Text(
                        modifier = Modifier
                            .fillMaxWidth(0.88f)
                            .padding(10.dp),
                        text = selectedDate.toString(),
                        fontSize = 16.sp,
                        fontFamily = poppinsFam
                    )
                }
            }

            Spacer(modifier = Modifier.weight(0.1f))

            Button(
                modifier = Modifier
                    .fillMaxWidth(0.88f),
                onClick = {
                    if (
                        name.isNotEmpty() &&
                        username.isNotEmpty() &&
                        email.isNotEmpty() &&
                        password.isNotEmpty()
                    ){
                        authViewmodel.RegisterUser(name, username, email, password, selectedDate)
                    }else{
                        Toast.makeText(context, "No field should be left empty", Toast.LENGTH_LONG).show()
                    }
                },
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFD3B3F8),
                    contentColor = Color.Black
                ),
                border = BorderStroke(1.dp, Color.Black),
                elevation = ButtonDefaults.elevatedButtonElevation(10.dp)
            ) {
                Text(
                    text = "Register",
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
                    containerColor = Color(0xFFD3B3F8),
                    contentColor = Color.Black
                ),
                border = BorderStroke(1.dp, Color.Black),
                onClick = {
                    navController.navigate("login_screen")
                },
                elevation = ButtonDefaults.elevatedButtonElevation(10.dp)
            ) {
                Text(
                    text = "Already registered to spaces?",
                    fontSize = 15.sp,
                    fontFamily = poppinsFam
                )
            }

            Spacer(modifier = Modifier.weight(0.5f))
        }
    }
}