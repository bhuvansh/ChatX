package com.example.chatbotapp.screen

sealed class Screen(val route:String){

    object SignInScreen:Screen("signinscreen")
    object SignUpScreen:Screen("signupscreen")
    object chatroomScreen:Screen("chatroomscreen")
    object chatScreen:Screen("chatscreen")
}