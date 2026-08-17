package com.example.portfolio.navigation
sealed class Screen(
    val route : String
){
    object Welcome : Screen("Welcome")
    object RegisterProfile : Screen("RegisterProfile")
    object Register : Screen("Register")
    object RegisterComplete : Screen("RegisterComplete/{displayName}/{bio}") {
        fun createRoute(displayName: String, bio: String) = "RegisterComplete/$displayName/$bio"
    }
    object Login : Screen("Login")
    object SplashScreen : Screen("SplashScreen")
    object Main : Screen("Main")
    object Home : Screen("Home")
    object Skills : Screen("Skills")
    object Projects : Screen("Projects")
    object About : Screen("About")
    object Profile : Screen("Profile")
}
