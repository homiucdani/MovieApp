package com.example.movieapp.data.remote.dto.review

import com.squareup.moshi.Json

data class AuthorDetailsDto(
    @field:Json(name = "avatar_path")
    val avatarPath: String,
    val name: String,
    val rating: Double,
    val username: String
)