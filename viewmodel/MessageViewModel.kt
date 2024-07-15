package com.example.chatbotapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatbotapp.data.FirestoreInjection
import com.example.chatbotapp.data.Message
import com.example.chatbotapp.data.MessageRepository
import com.example.chatbotapp.data.Result
import com.example.chatbotapp.data.User
import com.example.chatbotapp.data.UserRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class MessageViewModel: ViewModel() {

    //for fetching the messages
    private val messageRepository:MessageRepository
    //user repository for fetching the info about the user
    private val userRepository:UserRepository
    init {
        messageRepository = MessageRepository(FirestoreInjection.instance())
        userRepository = UserRepository(FirebaseAuth.getInstance(),
            FirestoreInjection.instance())
        loadCurrentUser()
    }

    private val _messages = MutableLiveData<List<Message>>()
    val messages : LiveData<List<Message>> get()=_messages

    private val _roomId = MutableLiveData<String>()

    private val _currentUser = MutableLiveData<User>()
    val currentUser : LiveData<User> get()=_currentUser


    private fun loadCurrentUser(){
        viewModelScope.launch {
            when(val result = userRepository.getCurrentUser())
            {
                is Result.Success ->_currentUser.value = result.data
                is Result.Error ->{
                    //we can handle the error by showing a snackbar
                }
            }
        }
    }

    fun loadMessages(){
        viewModelScope.launch {
            if(_roomId!=null)
            {
                messageRepository.getChatMessage(_roomId.value.toString())
                    .collect{
                    _messages.value = it
                    }
            }
        }
    }

    fun sendMessage(text:String){

        println("the current room id with the messageViewModel is ${_roomId.value}")

        loadCurrentUser()
        println("the current logged in user is ${_currentUser.value}")
        if(_currentUser.value!=null){

            println("the first name is ${_currentUser}")

            val message = Message(
                senderFirstName = _currentUser.value!!.firstName,
                text=text,
                senderId = _currentUser.value!!.email)
            viewModelScope.launch {
                when(val result = _roomId.value?.let { messageRepository.sendMessage(it,message) }){
                    is Result.Success->Unit
                    is Result.Error ->{}
                    else->{}
                }
            }
        }
        else{
            println("no current user avalible")
        }
    }

    fun setRoomId(roomId:String){
        _roomId.value=roomId
        loadMessages()
    }

    fun setUser(){
        loadCurrentUser()
    }
}