package com.example.portfolio.features.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.portfolio.registration.AuthRepo
import com.example.portfolio.userProfile.UserProfile
import com.example.portfolio.userProfile.UserRepo
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface ProfileUiState {
    object Loading : ProfileUiState
    data class Success(val userProfile: UserProfile) : ProfileUiState
    data class Error(val message: String) : ProfileUiState
}

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepo: UserRepo,
    private val authRepo: AuthRepo
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Loading)
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        fetchProfile()
    }

    fun fetchProfile() {
        val uid = authRepo.provideUserId() ?: return
        val email = authRepo.provideUserEmail() ?: ""
        
        _uiState.update { ProfileUiState.Loading }
        
        viewModelScope.launch {
            val result = userRepo.getUserProfile(uid)
            result.onSuccess { profile ->
                if (profile != null) {
                    _uiState.update { ProfileUiState.Success(profile.copy(email = email)) }
                } else {
                    _uiState.update { ProfileUiState.Error("Profile not found") }
                }
            }
            result.onFailure { e ->
                _uiState.update { ProfileUiState.Error(e.localizedMessage ?: "Failed to fetch profile") }
            }
        }
    }

    fun updateProfile(displayName: String, bio: String) {
        val currentProfile = (uiState.value as? ProfileUiState.Success)?.userProfile ?: return
        
        _uiState.update { ProfileUiState.Loading }
        
        viewModelScope.launch {
            val updatedProfile = currentProfile.copy(
                displayName = displayName,
                bio = bio
            )
            val result = userRepo.saveUserProfile(updatedProfile)
            result.onSuccess {
                _uiState.update { ProfileUiState.Success(updatedProfile) }
            }
            result.onFailure { e ->
                _uiState.update { ProfileUiState.Error(e.localizedMessage ?: "Failed to update profile") }
            }
        }
    }

    fun signOut() {
        authRepo.signOut()
    }
}
