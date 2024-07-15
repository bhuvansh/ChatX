package com.example.chatbotapp.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chatbotapp.viewmodel.AuthViewModel

@Composable

fun SignUp(
    authViewModel:AuthViewModel,
    onNavigateToSignIn:()->Unit){

    var email by remember{mutableStateOf("")}
    var password by remember{mutableStateOf("")}
    var firstName by remember{ mutableStateOf("") }
    var lastName by remember{mutableStateOf("")}

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){

        OutlinedTextField(value = firstName,
            onValueChange ={firstName = it},
            modifier= Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            label={Text("First Name")}
        )
        OutlinedTextField(value = lastName,
            onValueChange ={lastName = it},
            modifier= Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            label={Text("Last Name")}
        )
        OutlinedTextField(value = email,
            onValueChange ={email = it},
            modifier= Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            label={Text("email")}
        )
        OutlinedTextField(value = password,
            onValueChange ={password = it},
            modifier= Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            label = {Text("Password")},
            visualTransformation = PasswordVisualTransformation()
        )

        //signup button
        Button(onClick = {
            authViewModel.signUp(email,password,firstName,lastName)
            email=""
            password=""
            firstName=""
            lastName=""
        },
            modifier= Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(50)) {
            Text("Signup")
        }

        Row(){
            Text("Already have an account?",
                modifier=Modifier
                    .align(Alignment.CenterVertically))
            TextButton(onClick = { onNavigateToSignIn() }) {
                Text("Sign in.",Modifier.padding(0.dp))
            }
        }

    }
}
