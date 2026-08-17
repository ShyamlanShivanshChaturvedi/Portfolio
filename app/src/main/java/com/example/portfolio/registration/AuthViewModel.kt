package com.example.portfolio.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.portfolio.userProfile.UserProfile
import com.example.portfolio.userProfile.UserRepo
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface AuthState{
    object Idle : AuthState
    object Loading : AuthState
    data class Success(val user : FirebaseUser?) : AuthState
    data class Failure(val message : String) : AuthState
}

sealed interface UserProfileState {
    object Idle : UserProfileState
    object Loading : UserProfileState
    data class Success(val userProfile : UserProfile?) : UserProfileState
    data class Failure(val message: String) : UserProfileState
}

sealed interface UserState {
    object Idle : UserState
    object Loading : UserState
    object Success : UserState
    data class Failure(val isUserProfile : Boolean, val isAuth : Boolean, val message : String) : UserState
}

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepo : AuthRepo,
    private val userRepo : UserRepo
) : ViewModel() {

    private val _currentState = MutableStateFlow<UserState>(UserState.Idle)
    val currentState : StateFlow<UserState> = _currentState.asStateFlow()

    init {
        observeUserState()
    }

    val currentUid : String?
        get() = authRepo.provideUserId()

    fun signIn(email : String, pass : String){
        _currentState.update {
            UserState.Loading
        }
        viewModelScope.launch {
            val result = authRepo.signIn(email,pass)
            result.onSuccess { user ->
                fetchProfile()
            }
            result.onFailure { exception ->

                _currentState.update {
                    UserState.Failure(isAuth = true, isUserProfile = false, message = exception.localizedMessage ?: "Unknown Error In Authorization")
                }

            }
        }
    }

    fun signUp(email : String, pass : String, displayName: String, bio: String){
        _currentState.update {
            UserState.Loading
        }
        viewModelScope.launch {
            val result = authRepo.signUp(email,pass)
            result.onSuccess { user ->

                saveProfile(displayName,bio)
            }
            result.onFailure { exception ->

                _currentState.update {
                    UserState.Failure(isAuth = true, isUserProfile = false, message = exception.localizedMessage ?: "Unknown Error In Authorization")
                }
            }
        }
    }

    fun signOut() {
        authRepo.signOut()
    }

    private fun observeUserState() {
        viewModelScope.launch {
            authRepo.getAuthState().collectLatest { user ->
                if (user != null){
                    fetchProfile()
                }
            }
        }
    }

    fun retryAuth(){
        _currentState.update {
            UserState.Idle
        }
    }

    fun saveProfile(displayName : String, bio : String) {
        val uid = currentUid ?: run {

            _currentState.update {
                UserState.Failure(isAuth = true, isUserProfile = false, message = "User is not logged in!")
            }
            return
        }

        _currentState.update {
            UserState.Loading
        }

        viewModelScope.launch {
            val newProfile = UserProfile(
                uid = uid,
                displayName = displayName,
                bio = bio
            )

            val result = userRepo.saveUserProfile(newProfile)

            result.onSuccess {
                _currentState.update {
                    UserState.Success
                }
            }
            result.onFailure { exception ->
                _currentState.update {
                    UserState.Failure(
                       message = exception.localizedMessage ?: "Failed to save profile",
                        isAuth = false,
                        isUserProfile = true
                    )
                }
            }
        }
    }

    fun fetchProfile(){
        val uid = currentUid ?: run {

            _currentState.update {
                UserState.Failure(isUserProfile = false, isAuth = true, message = "User is not logged in!")
            }

            return
        }

        _currentState.update {
            UserState.Loading
        }
        viewModelScope.launch {
            val result = userRepo.getUserProfile(uid)

            result.onSuccess { profile ->

                _currentState.update {
                    UserState.Success
                }
            }
            result.onFailure { exception ->

                _currentState.update {
                    UserState.Failure(isAuth = false, isUserProfile = true, message = exception.localizedMessage ?: "Unable to fetch profile.")
                }
            }
        }
    }
}