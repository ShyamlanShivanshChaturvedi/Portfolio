package com.example.portfolio.ui.utils

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.portfolio.R
import com.example.portfolio.navigation.Screen
import com.example.portfolio.ui.theme.*

@Composable
fun BottomNavBar(
    currentRoute: String,
    onNavItemClick: (String) -> Unit
) {
    val isDark = isSystemInDarkTheme()
    val bgColor = if (isDark) CardBg.copy(alpha = 0.98f) else Color.White
    val borderColor = if (isDark) Color.White.copy(alpha = 0.15f) else Color.Black.copy(alpha = 0.08f)

    val home = Screen.Home.route
    val projects = Screen.Projects.route
    val skills = Screen.Skills.route
    val about = Screen.About.route
    val profile = Screen.Profile.route

    val isProjectsSelected = currentRoute == projects
    

    val projectsSize = 64.dp
    val projectsOffset = (-24).dp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Surface(
            color = bgColor,
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
            tonalElevation = if (isDark) 0.dp else 8.dp,
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, borderColor, RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 12.dp, horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BottomNavItem(Icons.Default.Home, isSelected = currentRoute == home, onClick = { onNavItemClick(home) })
                BottomNavItem(Icons.Default.Code, isSelected = currentRoute == skills, onClick = { onNavItemClick(skills) })
                

                Spacer(modifier = Modifier.size(48.dp))

                BottomNavItem(Icons.Default.Bolt, isSelected = currentRoute == about, onClick = { onNavItemClick(about) })
                BottomNavItem(Icons.Default.Person, isSelected = currentRoute == profile, onClick = { onNavItemClick(profile) })
            }
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .offset(y = projectsOffset)
                .size(64.dp)
        ) {
            Surface(
                color = if (isProjectsSelected) CyanGlow else CyanGlow.copy(alpha = 0.2f),
                shape = CircleShape,
                tonalElevation = if (isProjectsSelected) 12.dp else 4.dp,
                modifier = Modifier
                    .size(projectsSize)
                    .border(
                        width = 1.dp,
                        color = if (isProjectsSelected) Color.Transparent else CyanGlow.copy(alpha = 0.4f),
                        shape = CircleShape
                    )
                    .clickable { onNavItemClick(projects) }
            ) {
                Icon(
                    imageVector = Icons.Default.RocketLaunch,
                    contentDescription = projects,
                    tint = if (isProjectsSelected) Color.Black else (if (isDark) Color.White else Color.Black),
                    modifier = Modifier
                        .padding(16.dp)
                        .scale(1.2f)
                )
            }
        }
    }
}

@Composable
fun BottomNavItem(icon: ImageVector, isSelected: Boolean, onClick: () -> Unit) {
    val isDark = isSystemInDarkTheme()
    
    val bubbleScale by animateFloatAsState(
        targetValue = if (isSelected) 1.6f else 0f,
        animationSpec = spring(dampingRatio = 0.5f, stiffness = 400f),
        label = "bubbleScale"
    )
    
    val iconColor by animateColorAsState(
        targetValue = if (isSelected) Color.Black else (if (isDark) Color.White.copy(alpha = 0.6f) else Color.Black.copy(alpha = 0.5f)),
        label = "iconColor"
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(44.dp)
            .clip(CircleShape)
            .clickable(onClick = onClick)
    ) {

        Box(
            modifier = Modifier
                .size(38.dp)
                .scale(bubbleScale)
                .background(CyanGlow, CircleShape)
        )
        
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview(name = "Dark Theme", showBackground = true, backgroundColor = 0xFF0B0E14)
@Composable
fun BottomNavBarDarkPreview() {
    val projects = stringResource(R.string.projects_nav)
    PortfolioTheme(darkTheme = true) {
        Box(modifier = Modifier.padding(top = 40.dp)) {
            BottomNavBar(currentRoute = projects, onNavItemClick = {})
        }
    }
}

@Preview(name = "Not Selected - Light", showBackground = true, backgroundColor = 0xFFF5F7FA)
@Composable
fun BottomNavBarNotSelectedPreview() {
    PortfolioTheme(darkTheme = false) {
        Box(modifier = Modifier.padding(top = 40.dp)) {
            BottomNavBar(currentRoute = "Home", onNavItemClick = {})
        }
    }
}
