package com.example.portfolio.features.about.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.portfolio.ui.theme.*

@Composable
fun AboutScreen(padding: PaddingValues) {
    val isDark = isSystemInDarkTheme()
    val textColor = if (isDark) Color.White else Color(0xFF1A1C1E)
    val glowColor = if (isDark) CyanGlow else LightCyanGlow

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
                        colors = listOf(glowColor.copy(alpha = if (isDark) 0.15f else 0.12f), Color.Transparent),
                        center = Offset(200f, 400f),
                        radius = 1200f
                    )
                )
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.height(48.dp))

                Surface(
                    color = accentColor(isDark).copy(alpha = 0.1f),
                    shape = CircleShape,
                    modifier = Modifier
                        .size(120.dp)
                        .border(2.dp, accentColor(isDark).copy(alpha = 0.3f), CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = accentColor(isDark),
                        modifier = Modifier.padding(32.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Shivansh Shyamlan",
                    color = textColor,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Android Developer & Cloud Developer",
                    color = accentColor(isDark),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                AboutSection(
                    title = "Who I Am",
                    icon = Icons.Default.Info,
                    content = "I'm a passionate Android and Cloud Developer dedicated to building modern, user-centric applications. I specialize in Jetpack Compose, Spring Boot, and cloud-native solutions that scale.",
                    isDark = isDark
                )

                Spacer(modifier = Modifier.height(16.dp))

                AboutSection(
                    title = "My Expertise",
                    icon = Icons.Default.Terminal,
                    content = "Expertise in crafting smooth UIs with Compose, building robust backends with Spring Boot, and managing cloud infrastructure with Firebase and beyond. I focus on clean architecture and high-performance code.",
                    isDark = isDark
                )

                Spacer(modifier = Modifier.height(16.dp))

                AboutSection(
                    title = "Let's Connect",
                    icon = Icons.Default.Email,
                    content = "I'm always open to discussing new projects, creative ideas, or opportunities to be part of your visions. Feel free to reach out via GitHub or Email!",
                    isDark = isDark
                )
                
                Spacer(modifier = Modifier.height(48.dp))
            }
        }
    }
}

@Composable
fun AboutSection(
    title: String,
    icon: ImageVector,
    content: String,
    isDark: Boolean
) {
    val cardBg = if (isDark) CardBg.copy(alpha = 0.7f) else Color.White
    val textColor = if (isDark) Color.White else Color(0xFF1A1C1E)
    val borderColor = if (isDark) Color.White.copy(alpha = 0.1f) else Color.Black.copy(alpha = 0.05f)

    Surface(
        color = cardBg,
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .widthIn(max = 600.dp)
            .fillMaxWidth()
            .border(1.dp, borderColor, RoundedCornerShape(24.dp))
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = accentColor(isDark),
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = title,
                    color = textColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = content,
                color = textColor.copy(alpha = 0.8f),
                fontSize = 15.sp,
                lineHeight = 22.sp
            )
        }
    }
}

@Composable
fun accentColor(isDark: Boolean): Color = if (isDark) CyanGlow else LightCyanGlow
