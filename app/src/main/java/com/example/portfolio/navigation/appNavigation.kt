package com.example.portfolio.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.portfolio.features.FeaturesScreen
import com.example.portfolio.registration.ui.RegistrationScreen
import com.example.portfolio.registration.ui.login.Login
import com.example.portfolio.registration.ui.register.Register
import com.example.portfolio.registration.ui.register.RegisterProfile
import com.example.portfolio.features.skills.ui.SkillsScreen
import com.example.portfolio.features.projects.ui.ProjectsScreen
import com.example.portfolio.splashScreen.ui.SplashScreen


@Composable
fun AppNavigation() {
    NavGraphContainer(startDestination = Screen.SplashScreen.route)
}

@Composable
fun NavGraphContainer(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {

        composable(Screen.SplashScreen.route) {
            SplashScreen(
                onNextFailure = {
                    navController.navigate(Screen.Welcome.route) {
                        popUpTo(Screen.SplashScreen.route) {
                            inclusive = true
                        }
                    }
                },
                onNextSuccess = { firebase, userProfile ->
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.SplashScreen.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(Screen.Welcome.route) {
            RegistrationScreen(
                onSignUpClick = { navController.navigate(Screen.Register.route) },
                onSignInClick = { navController.navigate(Screen.Login.route) }
            )
        }

        composable(Screen.Login.route) {
            Login(
                onNext = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Welcome.route) {
                            inclusive = true
                        }
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }

        navigation(
            startDestination = Screen.RegisterProfile.route,
            route = Screen.Register.route
        ) {
            composable(Screen.RegisterProfile.route) {
                RegisterProfile(
                    onNext = { name, bio ->
                        navController.navigate(Screen.RegisterComplete.createRoute(name, bio))
                    },
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }

            composable(
                route = Screen.RegisterComplete.route,
                arguments = listOf(
                    navArgument("displayName") { type = NavType.StringType },
                    navArgument("bio") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val name = backStackEntry.arguments?.getString("displayName") ?: ""
                val bio = backStackEntry.arguments?.getString("bio") ?: ""
                Register(
                    displayName = name,
                    bio = bio,
                    onNext = {
                        navController.navigate(Screen.Main.route) {
                            popUpTo(Screen.Welcome.route) {
                                inclusive = true
                            }
                        }
                    },
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }
        }

        composable(Screen.Main.route) {
            FeaturesScreen(
                onLogout = {
                    navController.navigate(Screen.Welcome.route) {
                        popUpTo(Screen.Main.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}
