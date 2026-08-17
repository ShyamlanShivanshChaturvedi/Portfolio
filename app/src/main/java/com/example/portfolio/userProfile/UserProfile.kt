package com.example.portfolio.userProfile

data class UserProfile(
    val uid : String = "",
    val displayName: String = "",
    val email : String = "",
    val bio : String = "",
    val createdAt : Long = System.currentTimeMillis()
)
