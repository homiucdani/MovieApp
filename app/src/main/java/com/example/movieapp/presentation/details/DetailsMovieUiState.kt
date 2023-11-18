package com.example.movieapp.presentation.details

import com.example.movieapp.domain.model.review.MovieReviewResult

data class DetailsMovieUiState(
    val reviews: MovieReviewResult,
    val expanded: Boolean = false
)
