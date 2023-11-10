package com.example.movieapp.data.remote.dto.review

import com.squareup.moshi.Json

data class MovieReviewDto(
    val id: Int,
    val page: Int,
    val results: List<MovieReviewResultDto>,
    @field:Json(name = "total_pages")
    val totalPages: Int,
    @field:Json(name = "total_results")
    val totalResults: Int
)