package com.example.chatbotapp.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await

class UserRepository(
    private val auth:FirebaseAuth,//for authenticating the user
    private val firestore:FirebaseFirestore //for adding the user in the database
) {

    // function to add the user to the database
    private suspend fun saveUserToFirestore(user:User){
        firestore.collection("users").document(user.email).set(user).await()
    }

    //function to signup a user 
    suspend fun signUp(email:String,
                       password:String,
                       firstName:String,
                       lastName:String) : Result<Boolean> {
        return try {
            //creating the user with the given mail id and password in the databsase
            auth.createUserWithEmailAndPassword(email, password).await()

            //adding the user to the database if user created sucessfully
            val user = User(firstName,lastName,email)
            saveUserToFirestore(user)
            //add user to firestore
            Result.Success(true)
        }
        catch (e: Exception) {
            Result.Error(e)
        }
    }

    //function to signin a user
    suspend fun signIn(email:String,password:String): Result<Boolean> =
        try {
            auth.signInWithEmailAndPassword(email,password).await()
            Result.Success(true)
        }
        catch(e:Exception){
            Result.Error(e)
        }


    suspend fun getCurrentUser(): Result<User> {
        return try {
            val currentUser = auth.currentUser
            if (currentUser != null) {
                val uid = currentUser.email
                if (uid != null) {
                    // Adding more logging to understand the process
                    println("Fetching user document for email: $uid")
                    val userDocument = firestore.collection("users").document(uid).get().await()
                    println("User document snapshot: $userDocument")

                    val user = userDocument.toObject(User::class.java)
                    if (user != null) {
                        println("User object: $user")
                        Result.Success(user)
                    } else {
                        println("Failed to convert document to User object")
                        Result.Error(Exception("User data not found in Firestore"))
                    }
                } else {
                    println("Current user email is null")
                    Result.Error(Exception("Current user email is null"))
                }
            } else {
                println("No authenticated user found")
                Result.Error(Exception("No authenticated user found"))
            }
        } catch (e: Exception) {
            println("Exception occurred: ${e.message}")
            Result.Error(e)
        }
    }

}