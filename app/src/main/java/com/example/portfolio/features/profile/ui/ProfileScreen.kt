package com.example.portfolio.features.profile.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.automirrored.filled.StickyNote2
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.portfolio.features.profile.ProfileUiState
import com.example.portfolio.features.profile.ProfileViewModel
import com.example.portfolio.ui.theme.*

@Composable
fun ProfileScreen(
    padding: PaddingValues,
    onLogout: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val isDark = isSystemInDarkTheme()
    val glowColor = if (isDark) CyanGlow else LightCyanGlow
    val secondGlow = if (isDark) PurpleGlow else LightPurpleGlow

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                if (isDark) Brush.verticalGradient(listOf(DeepNavy, Color.Black))
                else Brush.verticalGradient(listOf(Color.White, LightBg))
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        colors = listOf(glowColor.copy(alpha = if (isDark) 0.12f else 0.08f), Color.Transparent),
                        center = Offset(200f, 200f),
                        radius = 800f
                    )
                )
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        colors = listOf(secondGlow.copy(alpha = if (isDark) 0.1f else 0.06f), Color.Transparent),
                        center = Offset(1000f, 1000f),
                        radius = 1000f
                    )
                )
        )

        when (val state = uiState) {
            is ProfileUiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center), color = CyanGlow)
            }
            is ProfileUiState.Success -> {
                ProfileContentRedesign(
                    padding = padding,
                    userProfile = state.userProfile,
                    onUpdate = { name, bio -> viewModel.updateProfile(name, bio) },
                    onLogout = {
                        viewModel.signOut()
                        onLogout()
                    },
                    isDark = isDark
                )
            }
            is ProfileUiState.Error -> {
                ErrorView(state.message, onRetry = { viewModel.fetchProfile() })
            }
        }
    }
}

@Composable
fun ProfileContentRedesign(
    padding: PaddingValues,
    userProfile: com.example.portfolio.userProfile.UserProfile,
    onUpdate: (String, String) -> Unit,
    onLogout: () -> Unit,
    isDark: Boolean
) {
    var isEditing by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf(userProfile.displayName) }
    var bio by remember { mutableStateOf(userProfile.bio) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(modifier = Modifier.height(48.dp))
            
            PremiumHeader(isDark)

            Spacer(modifier = Modifier.height(32.dp))

            AnimatedContent(
                targetState = isEditing,
                transitionSpec = {
                    fadeIn(animationSpec = tween(300)) togetherWith fadeOut(animationSpec = tween(300))
                },
                label = "ProfileTransition"
            ) { editing ->
                if (editing) {
                    EditMode(
                        name = name,
                        bio = bio,
                        onNameChange = { name = it },
                        onBioChange = { bio = it },
                        isDark = isDark
                    )
                } else {
                    ViewMode(
                        displayName = userProfile.displayName,
                        email = userProfile.email,
                        bio = userProfile.bio,
                        isDark = isDark
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Row(
                modifier = Modifier.widthIn(max =600.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                PremiumButton(
                    text = if (isEditing) "Save Changes" else "Edit Profile",
                    icon = if (isEditing) Icons.Default.Check else Icons.Default.Edit,
                    modifier = Modifier.weight(1.2f),
                    isPrimary = true,
                    onClick = {
                        if (isEditing) onUpdate(name, bio)
                        isEditing = !isEditing
                    }
                )

                PremiumButton(
                    text = if (isEditing) "Cancel" else "Logout",
                    icon = if (isEditing) Icons.Default.Close else Icons.AutoMirrored.Filled.Logout,
                    modifier = Modifier.weight(0.8f),
                    isPrimary = false,
                    isCancel = isEditing,
                    onClick = {
                        if (isEditing) {
                            name = userProfile.displayName
                            bio = userProfile.bio
                            isEditing = false
                        } else {
                            onLogout()
                        }
                    }
                )
            }
            
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

@Composable
fun PremiumHeader(isDark: Boolean) {
    val glowColor = if (isDark) CyanGlow else LightCyanGlow
    val secondGlow = if (isDark) PurpleGlow else LightPurpleGlow

    Box(contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .size(130.dp)
                .background(
                    Brush.sweepGradient(listOf(glowColor, secondGlow, glowColor)),
                    CircleShape
                )
                .scale(1.05f)
                .clip(CircleShape)
                .background(Color.Transparent)
        )
        
        Surface(
            color = if (isDark) DeepNavy else Color.White,
            shape = CircleShape,
            modifier = Modifier
                .size(120.dp)
                .border(2.dp, Color.White.copy(alpha = 0.1f), CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = if (isDark) Color.White else Color.Black,
                modifier = Modifier
                    .padding(28.dp)
                    .scale(1.2f)
            )
        }
    }
}

@Composable
fun ViewMode(displayName: String, email: String, bio: String, isDark: Boolean) {
    val textColor = if (isDark) Color.White else Color(0xFF1A1C1E)

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = displayName,
            color = textColor,
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            text = email,
            color = if (isDark) CyanGlow.copy(alpha = 0.8f) else LightCyanGlow,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(32.dp))

        PremiumInfoCard(
            title = "BIO",
            content = bio,
            icon = Icons.Default.Description,
            isDark = isDark
        )
    }
}

@Composable
fun EditMode(
    name: String,
    bio: String,
    onNameChange: (String) -> Unit,
    onBioChange: (String) -> Unit,
    isDark: Boolean
) {
    Column(verticalArrangement = Arrangement.spacedBy(20.dp), modifier = Modifier.widthIn(max = 600.dp)) {
        PremiumTextField(
            value = name,
            onValueChange = onNameChange,
            label = "Name",
            icon = Icons.Default.Badge,
            isDark = isDark
        )
        
        PremiumTextField(
            value = bio,
            onValueChange = onBioChange,
            label = "Bio",
            icon = Icons.AutoMirrored.Filled.StickyNote2,
            isDark = isDark,
            singleLine = false
        )
    }
}

@Composable
fun PremiumTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector,
    isDark: Boolean,
    singleLine: Boolean = true,
    minLines: Int = 1
) {
    val textColor = if (isDark) Color.White else Color(0xFF1A1C1E)
    
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = textColor.copy(alpha = 0.5f)) },
        leadingIcon = { Icon(icon, contentDescription = null, tint = CyanGlow, modifier = Modifier.size(20.dp)) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        singleLine = singleLine,
        minLines = minLines,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = textColor,
            unfocusedTextColor = textColor,
            focusedBorderColor = CyanGlow,
            unfocusedBorderColor = textColor.copy(alpha = 0.15f),
            focusedContainerColor = if (isDark) Color.White.copy(alpha = 0.03f) else Color.Black.copy(alpha = 0.02f)
        )
    )
}

@Composable
fun PremiumInfoCard(title: String, content: String, icon: ImageVector, isDark: Boolean) {
    val cardBg = if (isDark) CardBg.copy(alpha = 0.8f) else Color.White
    val textColor = if (isDark) Color.White else Color(0xFF1A1C1E)
    val borderColor = if (isDark) Color.White.copy(alpha = 0.12f) else Color.Black.copy(alpha = 0.08f)

    Surface(
        color = cardBg,
        shape = RoundedCornerShape(28.dp),
        modifier = Modifier
            .widthIn(max = 600.dp)
            .fillMaxWidth()
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(listOf(borderColor, Color.Transparent, borderColor)),
                shape = RoundedCornerShape(28.dp)
            )
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = CyanGlow,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = title,
                    color = textColor.copy(alpha = 0.6f),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.5.sp
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = content,
                color = textColor,
                fontSize = 16.sp,
                lineHeight = 24.sp
            )
        }
    }
}

@Composable
fun PremiumButton(
    text: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    isPrimary: Boolean,
    isCancel: Boolean = false,
    onClick: () -> Unit
) {
    val brush = when {
        isPrimary -> Brush.horizontalGradient(listOf(CyanGlow, PurpleGlow))
        isCancel -> Brush.horizontalGradient(listOf(Color.White.copy(alpha = 0.1f), Color.White.copy(alpha = 0.2f)))
        else -> Brush.horizontalGradient(listOf(Color.Red.copy(alpha = 0.7f), Color.Red.copy(alpha = 0.9f)))
    }

    val borderModifier = if (isCancel) {
        Modifier.border(1.dp, Color.White.copy(alpha = 0.15f), RoundedCornerShape(20.dp))
    } else Modifier

    Surface(
        modifier = modifier
            .height(56.dp)
            .then(borderModifier)
            .clip(RoundedCornerShape(20.dp))
            .clickable(onClick = onClick),
        color = Color.Transparent
    ) {
        Box(
            modifier = Modifier
                .background(brush)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = text,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun ErrorView(message: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(Icons.Default.ErrorOutline, contentDescription = null, tint = Color.Red, modifier = Modifier.size(64.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = message, color = Color.White, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onRetry, colors = ButtonDefaults.buttonColors(containerColor = CyanGlow)) {
            Text("Retry")
        }
    }
}
