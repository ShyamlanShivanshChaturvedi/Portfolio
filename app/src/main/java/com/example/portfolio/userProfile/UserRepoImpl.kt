package com.example.portfolio.userProfile

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepoImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : UserRepo{
    private val userCollection = firestore.collection("users")

    override suspend fun saveUserProfile(userProfile: UserProfile): Result<Unit> {
        return try{
            userCollection.document(userProfile.uid)
                .set(userProfile)
                .await()
            Result.success(Unit)
        } catch (e : Exception){
            Result.failure(e)
        }
    }

    override suspend fun getUserProfile(uid: String): Result<UserProfile?> {
        return try {
            val snapshot = userCollection.document(uid).get().await()
            val data = snapshot.toObject(UserProfile::class.java)
            Result.success(data)
        } catch (e : Exception){
            Result.failure(e)
        }
    }

}