package com.example.portfolio.features.home.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.portfolio.ui.theme.CardBg
import com.example.portfolio.ui.theme.LightAccent

@Composable
fun HomeActions(isDark: Boolean, onProjectClick: () -> Unit = {}, onAboutClick: () -> Unit = {}) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        ActionCard(
            title = "Project Cards",
            icon = Icons.Default.GridView,
            isDark = isDark,
            onClick = onProjectClick
        )
        ActionCard(
            title = "About",
            icon = Icons.Default.Bolt,
            isDark = isDark,
            onClick = onAboutClick
        )
    }
}

@Composable
fun ActionCard(title: String, icon: ImageVector, isDark: Boolean, onClick: () -> Unit = {}) {
    val cardBg = if (isDark) CardBg.copy(alpha = 0.6f) else Color.White
    val textColor = if (isDark) Color.White else Color(0xFF1A1C1E)
    val iconBg = if (isDark) Color.White.copy(alpha = 0.1f) else LightAccent
    val borderColor = if (isDark) Color.Transparent else Color.Black.copy(alpha = 0.08f)

    Surface(
        color = cardBg,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .widthIn(max = 600.dp)
            .fillMaxWidth()
            .border(1.dp, borderColor, RoundedCornerShape(20.dp))
    ) {
        Row(
            modifier = Modifier
                .clickable { onClick() }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                color = iconBg,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = textColor,
                    modifier = Modifier.padding(8.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                color = textColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = textColor.copy(alpha = 0.5f)
            )
        }
    }
}
