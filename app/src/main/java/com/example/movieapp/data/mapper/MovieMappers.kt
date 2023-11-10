package com.example.movieapp.data.mapper

import com.example.movieapp.data.remote.dto.DatesDto
import com.example.movieapp.data.remote.dto.details.GenreDto
import com.example.movieapp.data.remote.dto.details.MovieDetailsDto
import com.example.movieapp.data.remote.dto.MovieDto
import com.example.movieapp.data.remote.dto.MovieResultDto
import com.example.movieapp.data.remote.dto.review.AuthorDetailsDto
import com.example.movieapp.data.remote.dto.review.MovieReviewDto
import com.example.movieapp.data.remote.dto.review.MovieReviewResultDto
import com.example.movieapp.data.remote.dto.search.SearchDto
import com.example.movieapp.data.remote.dto.search.SearchResultDto
import com.example.movieapp.domain.model.Dates
import com.example.movieapp.domain.model.details.Genre
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.model.details.MovieDetails
import com.example.movieapp.domain.model.MovieResult
import com.example.movieapp.domain.model.review.AuthorDetails
import com.example.movieapp.domain.model.review.MovieReview
import com.example.movieapp.domain.model.review.MovieReviewResult
import com.example.movieapp.domain.model.search.Search
import com.example.movieapp.domain.model.search.SearchResult

fun DatesDto.toDates(): Dates {
    return Dates(
        maximum = maximum,
        minimum = minimum
    )
}

fun MovieResultDto.toMovieResult(): MovieResult {
    return MovieResult(
        adult,
        backdropPath,
        genreIds,
        id,
        originalLanguage,
        originalTitle,
        overview,
        popularity,
        posterPath,
        releaseDate,
        title,
        video,
        voteAverage,
        voteCount
    )
}

fun MovieDto.toMovie(): Movie {
    return Movie(
        dates = dates.toDates(),
        results = results.map { it.toMovieResult() },
        page = page,
        totalPages = totalPages,
        totalResults = totalResults
    )
}

fun GenreDto.toGenre(): Genre {
    return Genre(id, name)
}

fun MovieDetailsDto.toMovieDetails(): MovieDetails {
    return MovieDetails(
        adult,
        backdropPath,
        budget,
        genres.map { it.toGenre() },
        homepage,
        id,
        imdbId,
        originalLanguage,
        originalTitle,
        overview,
        popularity,
        posterPath,
        releaseDate,
        revenue,
        runtime,
        status,
        tagline,
        title,
        video,
        voteAverage,
        voteCount
    )
}

fun AuthorDetailsDto.toAuthorDetails(): AuthorDetails {
    return AuthorDetails(avatarPath, name, rating, username)
}

fun MovieReviewResultDto.toMovieReviewResult(): MovieReviewResult {
    return MovieReviewResult(
        author,
        authorDetails.toAuthorDetails(),
        content,
        createdAt,
        id,
        updatedAt,
        url
    )
}

fun MovieReviewDto.toMovieReview(): MovieReview {
    return MovieReview(
        id,
        page,
        results.map { it.toMovieReviewResult() },
        totalPages,
        totalResults
    )
}

fun SearchResultDto.toSearchResult(): SearchResult {
    return SearchResult(
        adult = adult,
        backdropPath = backdropPath,
        genreIds = genreIds,
        id = id,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}

fun SearchDto.toSearch(): Search {
    return Search(
        page,
        results.map { it.toSearchResult() },
        totalPages,
        totalResults
    )
}