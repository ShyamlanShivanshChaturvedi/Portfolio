package com.example.portfolio.features.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.portfolio.R
import com.example.portfolio.ui.theme.CyanGlow
import com.example.portfolio.ui.theme.LightCyanGlow
import com.example.portfolio.ui.theme.LightPurpleGlow
import com.example.portfolio.ui.theme.PurpleGlow

@Composable
fun HomeHeader(isDark: Boolean) {
    val glowColor = if (isDark) CyanGlow else LightCyanGlow
    val purpleColor = if (isDark) PurpleGlow else LightPurpleGlow
    val badgeBg = if (isDark) Color.White.copy(alpha = 0.1f) else Color.Black.copy(alpha = 0.05f)
    val badgeTextColor = if (isDark) Color.White else Color.Black

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .border(
                    width = 2.dp,
                    brush = Brush.linearGradient(listOf(glowColor, purpleColor)),
                    shape = CircleShape
                )
                .padding(6.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.app_logo),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Surface(
            color = badgeBg,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.border(0.5.dp, badgeTextColor.copy(alpha = 0.2f), RoundedCornerShape(16.dp))
        ) {
            Text(
                text = "Android & Cloud Developer",
                color = badgeTextColor,
                fontSize = 12.sp,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                fontWeight = FontWeight.Medium
            )
        }
    }
}