package com.example.movieapp.presentation.home.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.LazyPagingItems
import com.example.movieapp.R
import com.example.movieapp.domain.model.MovieResult


@Composable
fun PopularMovieCard(
    popularMovies: LazyPagingItems<MovieResult>
) {
    MovieCard(
        movieCardTitle = stringResource(R.string.popular),
        movies = popularMovies
    )
}