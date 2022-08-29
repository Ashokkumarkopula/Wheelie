package com.example.wheeliee.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.wheeliee.presentation.HomeScreen
import com.example.wheeliee.presentation.LocationInfoScreen
import com.example.wheeliee.presentation.RestaurantDetails
import com.example.wheeliee.presentation.SignInScreen

@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Screen.SignInScreen.route) {
        composable(route = Screen.SignInScreen.route) {
            SignInScreen(navHostController = navHostController)
        }
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navHostController = navHostController)
        }
        composable(
            route = Screen.LocationInfoScreen.route + "/{location}",
            arguments = listOf(
                navArgument("location") {
                    type = NavType.StringType
                }
            )
        ) {
            val location = it.arguments?.getString("location") ?: ""
            LocationInfoScreen(
                location = location,
                navHostController = navHostController
            )
        }
        composable(
            route = Screen.RestaurantDetailsScreen.route + "/{location}/{index}",
            arguments = listOf(
                navArgument("location") {
                    type = NavType.StringType
                },
                navArgument("index") {
                    type = NavType.IntType
                }
            )
        ) {
            val location = it.arguments?.getString("location") ?: ""
            val index = it.arguments?.getInt("index") ?: 0
            RestaurantDetails(
                location = location,
                index = index,
                navHostController = navHostController
            )
        }
    }
}