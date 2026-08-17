package com.example.portfolio.features.skills.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.portfolio.R
import com.example.portfolio.features.skills.model.Skill
import com.example.portfolio.ui.theme.*
import com.example.portfolio.ui.utils.BottomNavBar

@Composable
fun SkillsScreen(
    padding: PaddingValues
) {
    val isDark = isSystemInDarkTheme()
    val textColor = if (isDark) Color.White else Color(0xFF1A1C1E)
    val subTextColor = if (isDark) Color.White.copy(alpha = 0.7f) else Color(0xFF42474E)
    val glowColor = if (isDark) CyanGlow else LightCyanGlow

    val skills = listOf(
        Skill(
            "Kotlin",
            "Expert-level development specializing in modern Android architectures, leveraging Coroutines and Flow for high-performance apps.",
            Icons.Default.Code
        ),
        Skill(
            "Android",
            "Comprehensive experience in native development using Material 3 and modern architectural patterns like MVVM to build scalable apps.",
            Icons.Default.Android
        ),
        Skill(
            "Jetpack Compose",
            "Proficiency in building modern, declarative, and highly interactive user interfaces with a focus on animations and performance.",
            Icons.Default.Layers
        ),
        Skill(
            "Java",
            "Deep-rooted understanding of Java and OOP principles, used for developing enterprise-grade applications and handling robust logic.",
            Icons.Default.DataArray
        ),
        Skill(
            "Spring Boot",
            "Proficiency in building robust, production-ready backend services and RESTful APIs, focusing on security and microservices.",
            Icons.Default.Terminal
        ),
        Skill(
            "C / C++",
            "Strong foundation in low-level programming and system optimization, with a focus on solving complex DSA problems.",
            Icons.Default.SettingsEthernet
        ),
        Skill(
            "Golang",
            "Hands-on experience in building highly concurrent and performant cloud-native applications using Go's concurrency primitives.",
            Icons.Default.Cloud
        ),
        Skill(
            "PostgreSQL",
            "Advanced knowledge of relational database management, focusing on schema design and query optimization for complex apps.",
            Icons.Default.Storage
        ),
    )

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
                        colors = listOf(glowColor.copy(alpha = if (isDark) 0.12f else 0.1f), Color.Transparent),
                        center = Offset(200f, 400f),
                        radius = 800f
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            
            Text(
                text = "Technical Skills",
                color = textColor,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Tools and technologies I use to build solutions.",
                color = subTextColor,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 200.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(skills) { skill ->
                    SkillCard(skill, isDark)
                }

                item {
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}

@Composable
fun SkillCard(skill: Skill, isDark: Boolean) {
    val cardBg = if (isDark) CardBg.copy(alpha = 0.7f) else Color.White
    val textColor = if (isDark) Color.White else Color(0xFF1A1C1E)
    val borderColor = if (isDark) Color.White.copy(alpha = 0.1f) else Color.Black.copy(alpha = 0.05f)
    val accentColor = if (isDark) CyanGlow else LightCyanGlow

    Surface(
        color = cardBg,
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, borderColor, RoundedCornerShape(24.dp))
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(accentColor.copy(alpha = 0.1f), CircleShape)
                    .border(0.5.dp, accentColor.copy(alpha = 0.3f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = skill.icon,
                    contentDescription = null,
                    tint = accentColor,
                    modifier = Modifier.size(24.dp)
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = skill.title,
                color = textColor,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = skill.info,
                color = textColor.copy(alpha = 0.6f),
                fontSize = 13.sp,
                lineHeight = 18.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SkillsPreview() {
    PortfolioTheme(darkTheme = true) {
        SkillsScreen(padding = PaddingValues(0.dp))
    }
}

