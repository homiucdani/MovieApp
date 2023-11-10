package com.example.movieapp.data.remote.dto.review

import com.squareup.moshi.Json

data class MovieReviewResultDto(
    val author: String,
    @field:Json(name = "author_details")
    val authorDetails: AuthorDetailsDto,
    val content: String,
    @field:Json(name = "created_at")
    val createdAt: String,
    val id: String,
    @field:Json(name = "updated_at")
    val updatedAt: String,
    val url: String
)