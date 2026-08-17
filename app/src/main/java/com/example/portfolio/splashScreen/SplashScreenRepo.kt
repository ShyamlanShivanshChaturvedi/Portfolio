package com.example.portfolio.splashScreen

import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface SplashScreenRepo {
    suspend fun getAuthState() : Flow<FirebaseUser?>
    fun provideUserId() : String?
}