package com.example.portfolio.features.projects.model

import androidx.compose.ui.graphics.vector.ImageVector

data class Project(
    val title: String,
    val description: String,
    val techStack: List<String>,
    val icon: ImageVector,
    val onClick : () -> Unit
)
