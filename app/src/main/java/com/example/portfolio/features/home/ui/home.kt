package com.example.portfolio.features.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.portfolio.ui.theme.*
import com.example.portfolio.ui.utils.BottomNavBar
import com.example.portfolio.R
import com.example.portfolio.navigation.Screen

@Composable
fun Home(
    padding: PaddingValues,
    onNavigate: (String) -> Unit = {}
) {
    val isDark = isSystemInDarkTheme()
    val textColor = if (isDark) Color.White else Color(0xFF1A1C1E)
    val subTextColor = if (isDark) Color.White.copy(alpha = 0.7f) else Color(0xFF42474E)
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
                        center = Offset(500f, 200f),
                        radius = 900f
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            HomeHeader(isDark)
            Spacer(modifier = Modifier.height(24.dp))
            HomeInfo(textColor, subTextColor)
            Spacer(modifier = Modifier.height(24.dp))
            SocialLinks(isDark)
            Spacer(modifier = Modifier.height(32.dp))
            HomeActions(
                isDark = isDark,
                onProjectClick = { onNavigate(Screen.Projects.route) },
                onAboutClick = { onNavigate(Screen.About.route) }
            )
            Spacer(modifier = Modifier.height(32.dp))
            MessageButton()
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}








@Preview(name = "Home Search Selected", showBackground = true)
@Composable
fun HomeProjectSelectedPreview() {
    PortfolioTheme(darkTheme = true) {
        Home(padding = PaddingValues(0.dp))
    }
}

@Preview(name = "Home Light", showBackground = true)
@Composable
fun HomeLightPreview() {
    PortfolioTheme(darkTheme = false) {
        Home(padding = PaddingValues(0.dp))
    }
}
