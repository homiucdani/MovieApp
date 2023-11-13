package com.example.movieapp.domain.repository

import androidx.paging.PagingData
import com.example.movieapp.domain.model.MovieResult
import com.example.movieapp.domain.model.details.MovieDetails
import com.example.movieapp.domain.model.review.MovieReview
import com.example.movieapp.domain.model.search.Search
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getTrendingMovies(): Flow<PagingData<MovieResult>>

    suspend fun getPopularMovies(): Flow<PagingData<MovieResult>>

    suspend fun getMovieById(movieId: Int): Result<MovieDetails>

    suspend fun getMovieReviews(movieId: Int): Result<MovieReview>

    suspend fun searchMovie(query: String): Result<Search>
}