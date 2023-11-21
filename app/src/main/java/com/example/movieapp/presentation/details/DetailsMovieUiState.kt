package com.example.movieapp.presentation.details

import com.example.movieapp.domain.model.review.MovieReviewResult

data class DetailsMovieUiState(
    val movieReview: MovieReviewResult,
    val expanded: Boolean = false
)
