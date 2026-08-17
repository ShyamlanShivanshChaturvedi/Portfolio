package com.example.portfolio.ui.utils

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.portfolio.R
import com.example.portfolio.ui.theme.*

@Composable
fun LoadingScreen(
    message: String = "Loading..."
) {
    val isDark = isSystemInDarkTheme()
    val bgColor = if (isDark) DeepNavy else LightBg
    val contentColor = if (isDark) Color.White else Color.Black
    
    val infiniteTransition = rememberInfiniteTransition(label = "loadingTransition")
    

    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )


    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseScale"
    )

    Scaffold(
        containerColor = bgColor
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(140.dp)
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .rotate(rotation)
                            .border(
                                width = 3.dp,
                                brush = Brush.sweepGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        CyanGlow.copy(alpha = 0.1f),
                                        CyanGlow,
                                        PurpleGlow,
                                        Color.Transparent
                                    )
                                ),
                                shape = CircleShape
                            )
                    )


                    Image(
                        painter = painterResource(id = R.drawable.app_logo),
                        contentDescription = "App Logo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(110.dp)
                            .scale(pulseScale)
                            .clip(CircleShape)
                            .border(1.dp, contentColor.copy(alpha = 0.1f), CircleShape)
                            .drawBehind {
                                drawCircle(
                                    brush = Brush.radialGradient(
                                        colors = listOf(
                                            CyanGlow.copy(alpha = 0.15f),
                                            Color.Transparent
                                        )
                                    ),
                                    radius = size.maxDimension / 1.5f
                                )
                            }
                    )
                }

                Spacer(modifier = Modifier.height(48.dp))
                

                Text(
                    text = message,
                    color = contentColor.copy(alpha = 0.6f),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    letterSpacing = 3.sp
                )
            }
        }
    }
}

@Preview(name = "Dark Enhanced Loading", showBackground = true, backgroundColor = 0xFF030508)
@Composable
fun LoadingScreenDarkPreview() {
    PortfolioTheme(darkTheme = true) {
        LoadingScreen()
    }
}

@Preview(name = "Light Enhanced Loading", showBackground = true, backgroundColor = 0xFFF0F4FF)
@Composable
fun LoadingScreenLightPreview() {
    PortfolioTheme(darkTheme = false) {
        LoadingScreen()
    }
}
