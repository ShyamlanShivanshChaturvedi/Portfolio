package com.example.portfolio.features.projects.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.portfolio.R
import com.example.portfolio.features.projects.model.Project
import com.example.portfolio.ui.theme.*
import com.example.portfolio.ui.utils.BottomNavBar

@Composable
fun ProjectsScreen(
    padding: PaddingValues
) {
    val isDark = isSystemInDarkTheme()
    val textColor = if (isDark) Color.White else Color(0xFF1A1C1E)
    val subTextColor = if (isDark) Color.White.copy(alpha = 0.7f) else Color(0xFF42474E)
    val glowColor = if (isDark) PurpleGlow else LightPurpleGlow

    val context = LocalContext.current
    val portfolioGithubProjectLink = stringResource(R.string.project_portfolio)

    val projects = listOf(
        Project(
            title = "Personal Portfolio App",
            description = "A modern, high-performance portfolio application built with Jetpack Compose. Features glassmorphic design, smooth animations, and Firebase integration.",
            techStack = listOf("Kotlin", "Jetpack Compose", "MVVM", "Firebase", "Hilt"),
            icon = Icons.Default.RocketLaunch,
            onClick = {
                if (portfolioGithubProjectLink.isNotEmpty()) {
                    try {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(portfolioGithubProjectLink))
                        context.startActivity(intent)
                    } catch (_: Exception) {
                    }
                }
            }
        )
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
                        colors = listOf(glowColor.copy(alpha = if (isDark) 0.15f else 0.12f), Color.Transparent),
                        center = Offset(800f, 200f),
                        radius = 1000f
                    )
                )
        )

        LazyColumn(
            modifier = Modifier
                .widthIn(max = 600.dp)
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = "Featured Projects",
                    color = textColor,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "A collection of my work and side projects.",
                    color = subTextColor,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Spacer(modifier = Modifier.height(32.dp))
            }

            items(projects) { project ->
                ProjectCard(project, isDark)
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
fun ProjectCard(project: Project, isDark: Boolean) {
    val cardBg = if (isDark) CardBg.copy(alpha = 0.7f) else Color.White
    val textColor = if (isDark) Color.White else Color(0xFF1A1C1E)
    val borderColor = if (isDark) Color.White.copy(alpha = 0.1f) else Color.Black.copy(alpha = 0.05f)
    val accentColor = if (isDark) CyanGlow else LightCyanGlow

    Surface(
        color = cardBg,
        shape = RoundedCornerShape(28.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, borderColor, RoundedCornerShape(28.dp))
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    color = accentColor.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        imageVector = project.icon,
                        contentDescription = null,
                        tint = accentColor,
                        modifier = Modifier.padding(12.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = project.title,
                    color = textColor,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = project.description,
                color = textColor.copy(alpha = 0.7f),
                fontSize = 14.sp,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            FlowRow(
                mainAxisSpacing = 8.dp,
                crossAxisSpacing = 8.dp
            ) {
                project.techStack.forEach { tech ->
                    TechChip(tech, isDark)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { project.onClick() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isDark) Color.White else Color.Black,
                    contentColor = if (isDark) Color.Black else Color.White
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Code, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("View on GitHub", fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Composable
fun TechChip(tech: String, isDark: Boolean) {
    Surface(
        color = if (isDark) Color.White.copy(alpha = 0.05f) else Color.Black.copy(alpha = 0.03f),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(
            width = 1.dp,
            color = if (isDark) Color.White.copy(alpha = 0.1f) else Color.Black.copy(alpha = 0.05f)
        )
    ) {
        Text(
            text = tech,
            color = if (isDark) Color.White.copy(alpha = 0.8f) else Color.Black.copy(alpha = 0.7f),
            fontSize = 12.sp,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowRow(
    mainAxisSpacing: Dp,
    crossAxisSpacing: Dp,
    content: @Composable () -> Unit
) {
    androidx.compose.foundation.layout.FlowRow(
        horizontalArrangement = Arrangement.spacedBy(mainAxisSpacing),
        verticalArrangement = Arrangement.spacedBy(crossAxisSpacing),
        content = { content() }
    )
}

@Preview(showBackground = true)
@Composable
fun ProjectsPreview() {
    PortfolioTheme(darkTheme = true) {
        ProjectsScreen(padding = PaddingValues(0.dp))
    }
}
