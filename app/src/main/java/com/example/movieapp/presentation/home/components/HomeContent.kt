package com.example.movieapp.presentation.home.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.example.movieapp.domain.model.MovieResult

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    trendingMovies: LazyPagingItems<MovieResult>,
    popularMovies: LazyPagingItems<MovieResult>,
    nowPlayingMovies: LazyPagingItems<MovieResult>,
) {
    LazyColumn(
        modifier = modifier
    ) {
        item {
            TrendingMovieCard(pagerState = pagerState, trendingMovies = trendingMovies)
        }

        item {
            PopularMovieCard(
                popularMovies = popularMovies,
            )
        }

        item {
            NowPlayingMovieCard(nowPlayingMovies = nowPlayingMovies)
        }
    }
}