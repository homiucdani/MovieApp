package com.example.movieapp.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.IntOffset
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movieapp.core.presentation.util.UiEvent
import com.example.movieapp.presentation.details.DetailsEvent
import com.example.movieapp.presentation.details.DetailsScreen
import com.example.movieapp.presentation.details.DetailsViewModel
import com.example.movieapp.presentation.home.HomeEvent
import com.example.movieapp.presentation.home.HomeScreen
import com.example.movieapp.presentation.home.HomeViewModel
import com.example.movieapp.presentation.search.SearchEvent
import com.example.movieapp.presentation.search.SearchScreen
import com.example.movieapp.presentation.search.SearchViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        home(
            navigateToDetails = { movieId ->
                navController.navigate(Screen.Details.passMovieId(movieId))
            },
            navigateToSearch = {
                navController.navigate(Screen.Search.route)
            }
        )
        details(
            onBackClick = {
                navController.popBackStack()
            }
        )

        search(
            navigateBack = {
                navController.popBackStack()
            },
            navigateToMovieDetails = { movieId ->
                navController.navigate(Screen.Details.passMovieId(movieId))
            }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
fun NavGraphBuilder.home(
    navigateToDetails: (Int) -> Unit,
    navigateToSearch: () -> Unit
) {
    composable(
        route = Screen.Home.route
    ) {
        val homeViewModel: HomeViewModel = hiltViewModel()
        val state = homeViewModel.state.collectAsState().value

        val trendingMovies = state.trendingMovies.collectAsLazyPagingItems()
        val popularMovies = state.trendingMovies.collectAsLazyPagingItems()
        val nowPlayingMovies = state.trendingMovies.collectAsLazyPagingItems()

        val pagerState = rememberPagerState {
            trendingMovies.itemCount
        }

        HomeScreen(
            state = state,
            pagerState = pagerState,
            trendingMovies = trendingMovies,
            popularMovies = popularMovies,
            nowPlayingMovies = nowPlayingMovies,
            onEvent = { event ->
                when (event) {
                    is HomeEvent.OnMovieClick -> {
                        navigateToDetails(event.movieId)
                    }

                    HomeEvent.NavigateToSearch -> {
                        navigateToSearch()
                    }
                }
            }
        )
    }
}

fun NavGraphBuilder.details(
    onBackClick: () -> Unit
) {
    composable(
        route = Screen.Details.route,
        arguments = listOf(
            navArgument(
                name = "movie_id"
            ) {
                type = NavType.IntType
            }
        ),
        enterTransition = {
            slideInHorizontally(
                animationSpec = tween(
                    durationMillis = 300
                ),
                initialOffsetX = { width ->
                    -width
                }
            )
        },
        exitTransition = {
            slideOut(
                tween(
                    durationMillis = 300
                ),
                targetOffset = { height ->
                    IntOffset(0, height.height)
                }
            )
        }
    ) {
        val detailsViewModel: DetailsViewModel = hiltViewModel()
        val state = detailsViewModel.state.collectAsState().value

        DetailsScreen(
            state = state,
            onEvent = { event ->
                when (event) {
                    DetailsEvent.OnBackClick -> {
                        onBackClick()
                    }

                    else -> detailsViewModel.onEvent(event)
                }
            }
        )
    }
}

fun NavGraphBuilder.search(
    navigateBack: () -> Unit,
    navigateToMovieDetails: (Int) -> Unit
) {
    composable(
        route = Screen.Search.route
    ) {
        val searchViewModel: SearchViewModel = hiltViewModel()
        val state = searchViewModel.state.collectAsState().value

        LaunchedEffect(key1 = true) {
            searchViewModel.uiEvent.collectLatest { event ->
                when (event) {
                    UiEvent.NavigateBack -> {
                        navigateBack()
                    }
                }
            }
        }

        SearchScreen(
            state = state,
            onEvent = { event ->
                when (event) {
                    is SearchEvent.NavigateToMovieDetails -> {
                        navigateToMovieDetails(event.movieId)
                    }

                    else -> searchViewModel.onEvent(event)
                }
            }
        )
    }
}