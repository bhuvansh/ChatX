package com.example.chatbotapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatbotapp.data.FirestoreInjection
import com.example.chatbotapp.data.Result
import com.example.chatbotapp.data.UserRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class AuthViewModel: ViewModel() {

    private val userRepository:UserRepository
    init{
        userRepository = UserRepository(
            FirebaseAuth.getInstance(),
            FirestoreInjection.instance()
        )
    }

    //variable to keep track of the authentication result
    private val _authResult = MutableLiveData<Result<Boolean>>()
    val authResult : LiveData<Result<Boolean>> get() = _authResult

    fun signUp(email:String,
               password:String,
               firstName:String,
               lastName:String)
    {
        viewModelScope.launch {
            _authResult.value = userRepository.signUp(email,password,firstName,lastName)
        }
    }

    fun signin(email:String, password:String){
        viewModelScope.launch {
            _authResult.value = userRepository.signIn(email,password)
        }
    }
}
