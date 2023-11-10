package com.example.movieapp.domain.model

data class Movie(
    val dates: Dates,
    val page: Int,
    val results: List<MovieResult>,
    val totalPages: Int,
    val totalResults: Int
)