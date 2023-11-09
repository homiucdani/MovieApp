package com.example.movieapp.data.remote.dto

import com.squareup.moshi.Json

data class MovieDto(
    val dates: DatesDto,
    val page: Int,
    val results: List<MovieResultDto>,
    @field:Json(name = "total_pages")
    val totalPages: Int,
    @field:Json(name = "total_results")
    val totalResults: Int
)