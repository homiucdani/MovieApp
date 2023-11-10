package com.example.movieapp.data.repository

import com.example.movieapp.data.mapper.toMovie
import com.example.movieapp.data.mapper.toMovieDetails
import com.example.movieapp.data.mapper.toMovieReview
import com.example.movieapp.data.mapper.toSearch
import com.example.movieapp.data.remote.MovieApi
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.model.details.MovieDetails
import com.example.movieapp.domain.model.review.MovieReview
import com.example.movieapp.domain.model.search.Search
import com.example.movieapp.domain.repository.RemoteDataSource

class RemoteDataSourceImpl(
    private val movieApi: MovieApi
) : RemoteDataSource {

    override suspend fun getTrendingMovies(): Result<Movie> {
        return try {
            val result = movieApi.getTrendingMovies().toMovie()
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getPopularMovies(): Result<Movie> {
        return try {
            val result = movieApi.getPopularMovies().toMovie()
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getMovieById(movieId: Int): Result<MovieDetails> {
        return try {
            val result = movieApi.getMovieById(movieId = movieId).toMovieDetails()
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getMovieReviews(movieId: Int): Result<MovieReview> {
        return try {
            val result = movieApi.getMovieReviews(movieId = movieId).toMovieReview()
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun searchMovie(query: String): Result<Search> {
        return try {
            val result = movieApi.searchMovie(query).toSearch()
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}