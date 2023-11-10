package com.example.movieapp.data.remote.dto.details

import com.squareup.moshi.Json

data class BelongsToCollectionDto(
    @field:Json(name = "backdrop_path")
    val backdropPath: String,
    val id: Int,
    val name: String,
    @field:Json(name = "poster_path")
    val posterPath: String
)