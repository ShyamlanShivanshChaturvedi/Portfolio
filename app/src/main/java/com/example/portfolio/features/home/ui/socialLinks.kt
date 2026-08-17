package com.example.portfolio.features.home.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.portfolio.R
import com.example.portfolio.ui.theme.CyanGlow
import com.example.portfolio.ui.theme.LightCyanGlow
import com.example.portfolio.ui.theme.LightPurpleGlow
import com.example.portfolio.ui.theme.PurpleGlow


@Composable
fun SocialLinks(isDark: Boolean) {
    val iconColor = if (isDark) Color.White else Color.Black
    val bgColor = if (isDark) Color.White.copy(alpha = 0.1f) else Color.Black.copy(alpha = 0.05f)

    val githubLink = stringResource(R.string.github_link)
    val linkdinLink = stringResource(R.string.linkdin_link)
    val context = LocalContext.current
    Row(
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SocialIcon(
            painter = painterResource(id = R.drawable.github_color),
            label = "GitHub",
            onClick = {
                if (githubLink.isNotEmpty()) {
                    try {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(githubLink))
                        context.startActivity(intent)
                    } catch (_: Exception) {
                        // Handle exception (e.g. ActivityNotFoundException)
                    }
                }
            },
            color = iconColor,
            backgroundColor = bgColor,
            isDark = isDark
        )
        SocialIcon(
            painter = painterResource(id = R.drawable.linkedin_icon),
            label = "LinkedIn",
            onClick = {
                if (linkdinLink.isNotEmpty()) {
                    try {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(linkdinLink))
                        context.startActivity(intent)
                    } catch (_: Exception) {
                    }
                }
            },
            color = iconColor,
            backgroundColor = bgColor,
            isDark = isDark
        )
    }
}

@Composable
fun SocialIcon(
    painter: Painter,
    label: String,
    onClick : () -> Unit,
    color: Color,
    backgroundColor: Color,
    isDark: Boolean
) {
    val glowColor = if (isDark) CyanGlow else LightCyanGlow
    val purpleColor = if (isDark) PurpleGlow else LightPurpleGlow

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable {
            onClick()
        }
    ) {
        Surface(
            color = backgroundColor,
            shape = CircleShape,
            modifier = Modifier
                .size(70.dp)
                .border(
                    width = 1.dp,
                    brush = Brush.linearGradient(
                        listOf(glowColor.copy(alpha = 0.5f), purpleColor.copy(alpha = 0.5f))
                    ),
                    shape = CircleShape
                )
        ) {
            Icon(
                painter = painter,
                contentDescription = label,
                tint = Color.Unspecified,
                modifier = Modifier.padding(8.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            color = color.copy(alpha = 0.7f),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
