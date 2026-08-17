package com.example.portfolio.registration

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepoImpl @Inject constructor(
    private val auth : FirebaseAuth
) : AuthRepo {
    override suspend fun signIn(
        email: String,
        pass: String
    ): Result<FirebaseUser?> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, pass).await()
            Result.success(result.user)
        } catch (e : Exception){
            Result.failure(e)
        }
    }

    override suspend fun signUp(
        email: String,
        pass: String
    ): Result<FirebaseUser?> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, pass).await()
            Result.success(result.user)
        } catch (e : Exception){
            Result.failure(e)
        }
    }

    override fun signOut() {
        auth.signOut()
    }

    override fun getAuthState(): Flow<FirebaseUser?> = callbackFlow{
        val authStateListener = FirebaseAuth.AuthStateListener{ firebaseAUth ->
            trySend(firebaseAUth.currentUser)
        }
        auth.addAuthStateListener(authStateListener)

        awaitClose {
            auth.removeAuthStateListener (authStateListener)
        }
    }

    override fun provideUserId(): String? {
        return auth.currentUser?.uid
    }

    override fun provideUserEmail(): String? {
        return auth.currentUser?.email
    }

}