package com.example.movieapp.domain.model.review

data class MovieReview(
    val id: Int,
    val page: Int,
    val results: List<MovieReviewResult>,
    val totalPages: Int,
    val totalResults: Int
)
