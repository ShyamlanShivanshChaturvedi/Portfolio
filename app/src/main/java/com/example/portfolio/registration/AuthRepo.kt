package com.example.portfolio.registration

import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AuthRepo {
    suspend fun signIn(email : String, pass : String) : Result<FirebaseUser?>
    suspend fun signUp(email : String, pass : String) : Result<FirebaseUser?>
    fun signOut()
    fun getAuthState() : Flow<FirebaseUser?>
    fun provideUserId() : String?
    fun provideUserEmail() : String?
}