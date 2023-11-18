package com.example.movieapp.presentation.details

import com.example.movieapp.domain.model.details.MovieDetails

data class DetailsState(
    val movieDetails: MovieDetails? = null,
    val movieUiState: List<DetailsMovieUiState> = emptyList(),
    val error: String? = null,
    val isLoading: Boolean = false,
    val selectedTab: Int = 0
)
