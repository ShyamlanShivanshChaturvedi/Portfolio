package com.example.portfolio.di

import com.example.portfolio.registration.AuthRepo
import com.example.portfolio.registration.AuthRepoImpl
import com.example.portfolio.splashScreen.SplashScreenRepo
import com.example.portfolio.splashScreen.SplashScreenRepoImpl
import com.example.portfolio.userProfile.UserRepo
import com.example.portfolio.userProfile.UserRepoImpl
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    @Singleton
    fun provideAuthRepo(auth: FirebaseAuth): AuthRepo = AuthRepoImpl(auth)

    @Provides
    @Singleton
    fun provideUserRepo(firestore: FirebaseFirestore): UserRepo = UserRepoImpl(firestore)

    @Provides
    @Singleton
    fun provideSplashScreenRepo(auth : FirebaseAuth) : SplashScreenRepo = SplashScreenRepoImpl(auth)
}
