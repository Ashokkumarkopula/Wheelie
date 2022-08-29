package com.example.wheeliee.presentation.navigation

sealed class Screen(val route: String) {
    object SignInScreen : Screen("sign_in_screen")
    object HomeScreen : Screen("home_screen")
    object LocationInfoScreen : Screen("location_info_screen")
    object RestaurantDetailsScreen : Screen("restaurant_details_screen")
}
