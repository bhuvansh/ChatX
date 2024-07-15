package com.example.chatbotapp.data

data class User(
    val firstName:String,
    val lastName:String,
    val email:String
){
    // Default constructor
    constructor() : this("", "", "")
}
