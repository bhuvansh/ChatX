package com.example.chatbotapp.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.chatbotapp.viewmodel.AuthViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import com.example.chatbotapp.data.Result
import io.grpc.Context

@Composable
fun SignIn(
    authViewModel: AuthViewModel,
    onNavigateToSignUp:()->Unit,
    onSignInSuccess:()->Unit //to take the user to the chatroom after sucessful login
    ){
    var email by remember{mutableStateOf("")}
    var password by remember{mutableStateOf("")}

    val result by authViewModel.authResult.observeAsState()
    val context = LocalContext.current

    Column(modifier= Modifier
        .fillMaxSize()
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){

        OutlinedTextField(value = email,
            onValueChange ={email = it},
            modifier= Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            label={ Text("email") }
        )
        OutlinedTextField(value = password,
            onValueChange ={password = it},
            modifier= Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )

        //signin button
        Button(onClick = {

            authViewModel.signin(email, password)
            when (result) {
                is Result.Success -> {
                    onSignInSuccess()
                    email=""
                    password=""
                    Toast.makeText(context,"signin successful",Toast.LENGTH_SHORT).show()
                }

                is Result.Error -> {
                    Toast.makeText(context,"invalid email or password",Toast.LENGTH_LONG).show()
                }

                else -> {

                }
            }
        },
            modifier= Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(50)
        ) {
            Text("Sign in")
        }

        Row(){
            Text("Don't have an account?",modifier=Modifier.align(Alignment.CenterVertically))
            TextButton(onClick = { onNavigateToSignUp() }) {
                Text("Sign up.")
            }
        }
    }
}
