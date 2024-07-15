package com.example.chatbotapp.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.chatbotapp.viewmodel.AuthViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(
    authtViewModel:AuthViewModel,
    navController: NavController)
{
    NavHost(navController = navController as NavHostController,
        startDestination =Screen.SignUpScreen.route ) {

        composable(Screen.SignUpScreen.route) {
            SignUp(authViewModel = authtViewModel,
                onNavigateToSignIn = {
                    navController.navigate(Screen.SignInScreen.route)}
            )
        }

        composable(Screen.SignInScreen.route){
            SignIn (authtViewModel,
                onNavigateToSignUp = {navController.navigate(Screen.SignUpScreen.route)},
                    onSignInSuccess = {
                        navController.navigate(Screen.chatroomScreen.route)
                    }
                )
        }

        composable(Screen.chatroomScreen.route){
            ChatRoomListScreen(onJoinClicked = {
                println(it.id)
                navController.navigate("${Screen.chatScreen.route}/${it.id}/${it.name}")
            })
        }

        composable("${Screen.chatScreen.route}/{roomId}/{roomName}"){
            val roomId = it.arguments?.getString("roomId")?:""
            val roomName =it.arguments?.getString("roomName")?:""
            println("The roomId passed to the chat Room is :- $roomId")
            ChatScreen(roomId = roomId,roomName=roomName)
        }
    }
}