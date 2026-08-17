package com.example.portfolio.features

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.portfolio.features.about.ui.AboutScreen
import com.example.portfolio.features.home.ui.Home
import com.example.portfolio.features.profile.ui.ProfileScreen
import com.example.portfolio.features.projects.ui.ProjectsScreen
import com.example.portfolio.features.skills.ui.SkillsScreen
import com.example.portfolio.navigation.Screen
import com.example.portfolio.ui.utils.BottomNavBar
import com.example.portfolio.ui.utils.NavRail

@Composable
fun FeaturesScreen(onLogout: () -> Unit){
    val navController = rememberNavController()
    val adaptiveInfo = currentWindowAdaptiveInfo()
    val isWideScreen = !adaptiveInfo.windowSizeClass.windowWidthSizeClass.equals(WindowWidthSizeClass.COMPACT)

    Box(modifier = Modifier.fillMaxSize()){
        if (isWideScreen) {
            FeaturesScreenLarge(navController, onLogout)
        } else {
            FeaturesScreenSmall(navController, onLogout)
        }
    }
}

@Composable
fun FeaturesScreenSmall(navController: NavHostController, onLogout: () -> Unit) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: Screen.Home.route

    Scaffold(
        containerColor = Color.Transparent,
        bottomBar = {
            BottomNavBar(
                currentRoute = currentRoute,
                onNavItemClick = { destination ->
                    navigateTo(navController, currentRoute, destination)
                }
            )
        }
    ) { padding ->
        FeaturesNavHost(navController = navController, padding = padding, onLogout = onLogout)
    }
}

@Composable
fun FeaturesScreenLarge(navController: NavHostController, onLogout: () -> Unit){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: Screen.Home.route

    Scaffold(modifier = Modifier.fillMaxHeight()){ innerPadding ->
        Row(modifier = Modifier.fillMaxHeight()){
            NavRail(
                modifier = Modifier.fillMaxHeight(),
                currentRoute = currentRoute,
                onNext = { destination ->
                    navigateTo(
                        navController = navController,
                        currentRoute = currentRoute,
                        destination = destination
                    )
                }
            )

            Box(modifier = Modifier.fillMaxSize()){
                FeaturesNavHost(navController = navController, padding = innerPadding, onLogout = onLogout)
            }
        }
    }
}

@Composable
fun FeaturesNavHost(navController: NavHostController, padding: PaddingValues, onLogout: () -> Unit) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            Home(
                padding = padding,
                onNavigate = { destination ->
                    navigateTo(navController, Screen.Home.route, destination)
                }
            )
        }
        composable(Screen.Skills.route) {
            SkillsScreen(padding = padding)
        }
        composable(Screen.Projects.route) {
            ProjectsScreen(padding = padding)
        }
        composable(Screen.About.route) {
            AboutScreen(padding = padding)
        }
        composable(Screen.Profile.route) {
            ProfileScreen(padding = padding, onLogout = onLogout)
        }
    }
}

private fun navigateTo(navController: androidx.navigation.NavHostController, currentRoute: String, destination: String) {
    if (currentRoute != destination) {
        navController.navigate(destination) {
            popUpTo(Screen.Home.route) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}
