package com.example.movieapp.presentation.details

import com.example.movieapp.domain.model.details.MovieDetails
import com.example.movieapp.domain.model.review.MovieReviewResult

data class DetailsState(
    val movieDetails: MovieDetails? = null,
    val reviews: List<MovieReviewResult> = emptyList(),
    val error: String? = null,
    val isLoading: Boolean = false,
    val selectedTab: Int = 0
)
