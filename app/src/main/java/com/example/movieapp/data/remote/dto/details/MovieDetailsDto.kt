package com.example.movieapp.data.remote.dto.details

import com.squareup.moshi.Json

data class MovieDetailsDto(
    val adult: Boolean,
    @field:Json(name = "backdrop_path")
    val backdropPath: String,
    @field:Json(name = "belongs_to_collection")
    val belongsToCollection: BelongsToCollectionDto,
    val budget: Int,
    val genres: List<GenreDto>,
    val homepage: String,
    val id: Int,
    @field:Json(name = "imdb_id")
    val imdbId: String,
    @field:Json(name = "original_language")
    val originalLanguage: String,
    @field:Json(name = "original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @field:Json(name = "poster_path")
    val posterPath: String,
    @field:Json(name = "production_companies")
    val productionCompanies: List<ProductionCompanyDto>,
    @field:Json(name = "production_countries")
    val productionCountries: List<ProductionCountryDto>,
    @field:Json(name = "release_date")
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    @field:Json(name = "spoken_languages")
    val spokenLanguages: List<SpokenLanguageDto>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    @field:Json(name = "vote_average")
    val voteAverage: Double,
    @field:Json(name = "vote_count")
    val voteCount: Int
)