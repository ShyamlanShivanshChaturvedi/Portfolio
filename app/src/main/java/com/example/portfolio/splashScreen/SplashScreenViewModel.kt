package com.example.portfolio.splashScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.portfolio.userProfile.UserProfile
import com.example.portfolio.userProfile.UserRepo
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

sealed interface ResourceLoaderState {
    object Success : ResourceLoaderState
    object Loading : ResourceLoaderState
    object Failure : ResourceLoaderState
}

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val splashScreenRepo: SplashScreenRepo,
    private val userProfileRepo: UserRepo
) : ViewModel() {

    val currentUid: String?
        get() = splashScreenRepo.provideUserId()
    private val _currentState = MutableStateFlow<ResourceLoaderState>(ResourceLoaderState.Loading)
    val currentState: StateFlow<ResourceLoaderState> = _currentState.asStateFlow()

    private val _userProfile = MutableStateFlow<UserProfile?>(null)
    val userProfile: StateFlow<UserProfile?> = _userProfile.asStateFlow()

    private val _authState = MutableStateFlow<FirebaseUser?>(null)
    val authState: StateFlow<FirebaseUser?> = _authState.asStateFlow()

    init {
        observeUserState()
    }

    private fun observeUserState() {
        viewModelScope.launch {
            delay(1000L.milliseconds)
            splashScreenRepo.getAuthState().collectLatest { user ->
                if (user != null) {
                    _authState.update {
                        user
                    }
                    fetchProfile()  // fetch user will change the current state
                } else {
                    _currentState.update {
                        ResourceLoaderState.Failure
                    }
                }
            }
        }
    }

    private fun fetchProfile() {
        val uid = currentUid ?: run {

            _currentState.update {
                ResourceLoaderState.Failure
            }

            return
        }

        viewModelScope.launch {
            val result = userProfileRepo.getUserProfile(uid)

            result.onSuccess { profile ->
                _userProfile.update {
                    profile
                }
                _currentState.update {
                    ResourceLoaderState.Success
                }
            }
            result.onFailure { exception ->

                _currentState.update {
                    ResourceLoaderState.Failure
                }
            }
        }
    }
}
