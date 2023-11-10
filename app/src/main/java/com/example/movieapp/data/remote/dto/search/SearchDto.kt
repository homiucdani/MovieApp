package com.example.movieapp.data.remote.dto.search

import com.squareup.moshi.Json

data class SearchDto(
    val page: Int,
    val results: List<SearchResultDto>,
    @field:Json(name = "total_pages")
    val totalPages: Int,
    @field:Json(name = "total_results")
    val totalResults: Int
)