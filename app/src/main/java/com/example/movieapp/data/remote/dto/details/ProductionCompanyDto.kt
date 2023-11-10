package com.example.movieapp.data.remote.dto.details

import com.squareup.moshi.Json

data class ProductionCompanyDto(
    val id: Int,
    @field:Json(name = "logo_path")
    val logoPath: String,
    val name: String,
    @field:Json(name = "origin_country")
    val originCountry: String
)