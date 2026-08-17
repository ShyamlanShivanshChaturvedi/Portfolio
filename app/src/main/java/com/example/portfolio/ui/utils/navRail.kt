package com.example.portfolio.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemColors
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.portfolio.R
import com.example.portfolio.navigation.Screen
import com.example.portfolio.ui.theme.ButtonGradientStart
import com.example.portfolio.ui.theme.CyanGlow

@Composable
fun NavRail(
    currentRoute : String,
    onNext : (String) -> Unit,
    modifier : Modifier
){

    val navigationSelectedColor = CyanGlow

    NavigationRail(
        modifier = modifier
    ){

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            val home = stringResource(R.string.home_nav)
            val projects = stringResource(R.string.projects_nav)
            val skills = stringResource(R.string.skills_nav)
            val about = stringResource(R.string.about_nav)
            val profile = stringResource(R.string.profile_nav)

            Spacer(Modifier.height(2.dp))

            NavigationRailItem(
                icon = { Icon(Icons.Default.Home, contentDescription = home) },
                selected = currentRoute == Screen.Home.route,
                onClick = {
                    onNext(Screen.Home.route)
                },
                colors = NavigationRailItemDefaults.colors().copy(
                    selectedIndicatorColor = navigationSelectedColor,
                    selectedIconColor = Color.White
                )
            )

            Spacer(Modifier.height(2.dp))

            NavigationRailItem(
                icon = { Icon(Icons.Default.Code, contentDescription = skills) },
                selected = currentRoute == Screen.Skills.route,
                onClick = { onNext(Screen.Skills.route) },
                colors = NavigationRailItemDefaults.colors().copy(
                    selectedIndicatorColor = navigationSelectedColor,
                    selectedIconColor = Color.White
                )
            )

            Spacer(Modifier.height(15.dp))

            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .border(1.dp, Brush.verticalGradient(listOf(CyanGlow, ButtonGradientStart)), CircleShape)
                    .background(if (currentRoute == Screen.Projects.route) navigationSelectedColor else Color.Transparent)

            ){
                NavigationRailItem(
                    icon = { Icon(Icons.Default.RocketLaunch, contentDescription = projects) },
                    selected = currentRoute == Screen.Projects.route,
                    onClick = { onNext(Screen.Projects.route) },
                    colors = NavigationRailItemDefaults.colors().copy(
                        selectedIndicatorColor = navigationSelectedColor,
                        selectedIconColor = Color.White
                    )
                )
            }

            Spacer(Modifier.height(15.dp))

            NavigationRailItem(
                icon = { Icon(Icons.Default.Bolt, contentDescription = about) },
                selected = currentRoute == Screen.About.route,
                onClick = { onNext(Screen.About.route) },
                colors = NavigationRailItemDefaults.colors().copy(
                    selectedIndicatorColor = navigationSelectedColor,
                    selectedIconColor = Color.White
                )
            )

            Spacer(Modifier.height(2.dp))

            NavigationRailItem(
                icon = { Icon(Icons.Default.Person, contentDescription = profile) },
                selected = currentRoute == Screen.Profile.route,
                onClick = { onNext(Screen.Profile.route) },
                colors = NavigationRailItemDefaults.colors().copy(
                    selectedIndicatorColor = navigationSelectedColor,
                    selectedIconColor = Color.White
                )
            )

            Spacer(Modifier.height(2.dp))
        }
    }
}