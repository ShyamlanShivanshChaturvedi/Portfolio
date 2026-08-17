package com.example.portfolio.splashScreen

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class SplashScreenRepoImpl @Inject constructor(
    private val auth : FirebaseAuth
) : SplashScreenRepo{
    override suspend fun getAuthState(): Flow<FirebaseUser?>  = callbackFlow{
        val authStateListener = FirebaseAuth.AuthStateListener{ firebaseAuth ->
            trySend(firebaseAuth.currentUser)
        }
        auth.addAuthStateListener(authStateListener)

        awaitClose {
            auth.removeAuthStateListener (authStateListener)
        }
    }

    override fun provideUserId(): String? {
        return auth.currentUser?.uid
    }

}