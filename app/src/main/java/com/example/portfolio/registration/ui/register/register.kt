package com.example.portfolio.registration.ui.register

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.portfolio.registration.AuthState
import com.example.portfolio.registration.AuthViewModel
import com.example.portfolio.registration.UserState
import com.example.portfolio.ui.utils.LoadingScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Register(
    displayName : String,
    bio : String,
    onNext: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel(),
    onBack: () -> Unit
) {

    val currentState by viewModel.currentState.collectAsStateWithLifecycle()
    when (currentState) {
        UserState.Idle -> {
            RegisterScreen(
                error = null,
                onBack = onBack,
                viewModel = viewModel,
                displayName = displayName,
                bio = bio
            )
        }

        UserState.Loading -> {
            LoadingScreen("Registering you.....")
        }

        is UserState.Failure -> {
            BackHandler() {
                viewModel.retryAuth()
                onBack()
            }
            RegisterScreen(
                error = (currentState as UserState.Failure).message,
                onBack = onBack,
                viewModel = viewModel,
                displayName = displayName,
                bio = bio
            )
        }

        is UserState.Success -> {
            onNext()
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    displayName : String,
    bio : String,
    error: String? = null,
    onBack: () -> Unit,
    isSystemInDarkTheme: Boolean = isSystemInDarkTheme(),
    viewModel: AuthViewModel
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Create Account") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

        val buttonBackgroundColor = if (isSystemInDarkTheme) Color.White else Color.Black
        val buttonContentColor = if (isSystemInDarkTheme) Color.Black else Color.White
        val focusManager = LocalFocusManager.current

        var email by rememberSaveable { mutableStateOf("") }
        var pass by rememberSaveable { mutableStateOf("") }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(
                modifier = Modifier
                    .widthIn(max = 600.dp)
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                item {
                    Text(
                        text = "Tell us about yourself",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                }
                item {
                    Text(
                        text = "Enter your email and a strong password.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                item { Spacer(Modifier.height(32.dp)) }

                item {
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("email") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Email),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Down) }
                        ),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = buttonBackgroundColor,
                            unfocusedTextColor = buttonBackgroundColor,
                            unfocusedLabelColor = buttonBackgroundColor,
                            focusedLabelColor = buttonBackgroundColor,
                            focusedBorderColor = buttonBackgroundColor,
                            unfocusedBorderColor = buttonBackgroundColor
                        )
                    )
                }

                item { Spacer(Modifier.height(16.dp)) }

                item {
                    OutlinedTextField(
                        value = pass,
                        onValueChange = { pass = it },
                        label = { Text("password") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Password),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                if (email.isNotBlank() && pass.isNotBlank()) {
                                    viewModel.signUp(email = email, pass = pass, displayName = displayName, bio = bio)
                                    viewModel.saveProfile(displayName,bio)
                                }
                            }
                        ),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = buttonBackgroundColor,
                            unfocusedTextColor = buttonBackgroundColor,
                            unfocusedLabelColor = buttonBackgroundColor,
                            focusedLabelColor = buttonBackgroundColor,
                            focusedBorderColor = buttonBackgroundColor,
                            unfocusedBorderColor = buttonBackgroundColor
                        )
                    )
                }

                item { Spacer(Modifier.height(32.dp)) }

                item {
                    Text(
                        text = error ?: "",
                        color = MaterialTheme.colorScheme.error
                    )
                }
                if (error != null){ item { Spacer(Modifier.height(32.dp)) } }

                item {
                    Button(
                        onClick = {
                            viewModel.signUp(email = email, pass = pass, displayName = displayName, bio = bio)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        enabled = email.isNotBlank() && pass.isNotBlank(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = buttonBackgroundColor,
                            contentColor = buttonContentColor
                        )
                    ) {
                        Text("Register", style = MaterialTheme.typography.titleMedium)
                    }
                }
            }
        }
    }
}