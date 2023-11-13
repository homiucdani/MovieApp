package com.example.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.movieapp.presentation.home.HomeScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        home()
        details()
    }
}

fun NavGraphBuilder.home() {
    composable(
        route = Screen.Home.route
    ) {
        HomeScreen()
    }
}

fun NavGraphBuilder.details() {
    composable(
        route = Screen.Details.route,
        arguments = listOf(
            navArgument(
                name = "movie_id"
            ) {
                type = NavType.IntType
            }
        )
    ) {

    }
}