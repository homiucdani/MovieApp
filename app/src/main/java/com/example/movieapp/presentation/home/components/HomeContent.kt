package com.example.movieapp.presentation.home.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
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
    onCardClick: (Int) -> Unit
) {
    val handleLoadAndError = handleErrorContent(
        modifier = modifier,
        trendingMovies = trendingMovies,
        popularMovies = popularMovies,
        nowPlayingMovies = nowPlayingMovies
    )

    if (handleLoadAndError) {
        LazyColumn(
            modifier = modifier
        ) {
            item {
                TrendingMovieCard(
                    pagerState = pagerState,
                    trendingMovies = trendingMovies,
                    onCardClick = onCardClick
                )
            }

            item {
                PopularMovieCard(
                    popularMovies = popularMovies,
                    onCardClick = onCardClick
                )
            }

            item {
                NowPlayingMovieCard(
                    nowPlayingMovies = nowPlayingMovies,
                    onCardClick = onCardClick
                )
            }
        }
    }
}

@Composable
fun handleErrorContent(
    modifier: Modifier = Modifier,
    trendingMovies: LazyPagingItems<MovieResult>,
    popularMovies: LazyPagingItems<MovieResult>,
    nowPlayingMovies: LazyPagingItems<MovieResult>
): Boolean {

    val error = when {
        trendingMovies.loadState.refresh is LoadState.Error -> trendingMovies.loadState.refresh as LoadState.Error
        popularMovies.loadState.refresh is LoadState.Error -> trendingMovies.loadState.refresh as LoadState.Error
        nowPlayingMovies.loadState.refresh is LoadState.Error -> trendingMovies.loadState.refresh as LoadState.Error
        else -> null
    }

    val loading = when {
        trendingMovies.loadState.refresh is LoadState.Loading -> LoadState.Loading
        popularMovies.loadState.refresh is LoadState.Loading -> LoadState.Loading
        nowPlayingMovies.loadState.refresh is LoadState.Loading -> LoadState.Loading
        else -> null
    }

    return when {

        error != null -> {
            ErrorContent(
                modifier = modifier,
                error = error
            )
            false
        }

        loading != null -> {
            LoadingContent(modifier = modifier)
            false
        }

        else -> true
    }
}