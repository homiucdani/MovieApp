package com.example.movieapp.domain.repository

import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.model.details.MovieDetails
import com.example.movieapp.domain.model.review.MovieReview
import com.example.movieapp.domain.model.search.Search

interface RemoteDataSource {
    suspend fun getTrendingMovies(): Result<Movie>

    suspend fun getPopularMovies(): Result<Movie>

    suspend fun getMovieById(movieId: Int): Result<MovieDetails>

    suspend fun getMovieReviews(movieId: Int): Result<MovieReview>

    suspend fun searchMovie(query: String): Result<Search>
}