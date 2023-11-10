package com.example.movieapp.data.remote.dto.details

import com.squareup.moshi.Json

data class SpokenLanguageDto(
    @field:Json(name = "english_name")
    val englishName: String,
    val iso_639_1: String,
    val name: String
)