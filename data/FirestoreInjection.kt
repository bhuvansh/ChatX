package com.example.chatbotapp.data

import com.google.firebase.firestore.FirebaseFirestore


// Dependency Injection for FirebaseFirestore
object FirestoreInjection {

    private val instance: FirebaseFirestore by lazy{
        FirebaseFirestore.getInstance()
    }

    fun instance(): FirebaseFirestore {
        return instance
    }

}