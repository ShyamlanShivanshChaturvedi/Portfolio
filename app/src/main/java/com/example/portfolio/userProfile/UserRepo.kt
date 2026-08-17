package com.example.portfolio.userProfile

interface UserRepo {
    suspend fun saveUserProfile(userProfile: UserProfile) : Result<Unit>
    suspend fun getUserProfile(uid: String) : Result<UserProfile?>
}