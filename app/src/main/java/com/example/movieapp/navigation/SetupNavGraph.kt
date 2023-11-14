package com.example.movieapp.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movieapp.presentation.home.HomeScreen
import com.example.movieapp.presentation.home.HomeViewModel

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

@OptIn(ExperimentalFoundationApi::class)
fun NavGraphBuilder.home() {
    composable(
        route = Screen.Home.route
    ) {
        val homeViewModel: HomeViewModel = hiltViewModel()
        val state = homeViewModel.state.collectAsState().value
        val trendingMovies = state.trendingMovies.collectAsLazyPagingItems()
        val popularMovies = state.trendingMovies.collectAsLazyPagingItems()
        val nowPlayingMovies = state.trendingMovies.collectAsLazyPagingItems()

        val pagerState = rememberPagerState() {
            trendingMovies.itemCount
        }

        HomeScreen(
            state = state,
            pagerState = pagerState,
            trendingMovies = trendingMovies,
            popularMovies = popularMovies,
            nowPlayingMovies = nowPlayingMovies
        )
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