package com.example.movieapp.presentation.home

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.example.movieapp.domain.model.MovieResult
import com.example.movieapp.presentation.home.components.HomeContent
import com.example.movieapp.presentation.home.components.HomeTopAppBar

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    state: HomeState,
    pagerState: PagerState,
    trendingMovies: LazyPagingItems<MovieResult>,
    popularMovies: LazyPagingItems<MovieResult>,
    nowPlayingMovies: LazyPagingItems<MovieResult>,
    onEvent: (HomeEvent) -> Unit
) {
    Scaffold(
        topBar = {
            HomeTopAppBar(
                onSearchClick = {
                    onEvent(HomeEvent.NavigateToSearch)
                }
            )
        }
    ) { paddingValues ->
        HomeContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            pagerState = pagerState,
            trendingMovies = trendingMovies,
            popularMovies = popularMovies,
            nowPlayingMovies = nowPlayingMovies,
            onCardClick = { movieId ->
                onEvent(HomeEvent.OnMovieClick(movieId))
            }
        )
    }
}

