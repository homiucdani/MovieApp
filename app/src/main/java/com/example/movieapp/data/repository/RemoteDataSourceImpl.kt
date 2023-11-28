package com.example.movieapp.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movieapp.core.domain.util.Constants.ITEMS_PER_PAGE
import com.example.movieapp.data.mapper.toMovieDetails
import com.example.movieapp.data.mapper.toMovieReview
import com.example.movieapp.data.mapper.toSearch
import com.example.movieapp.data.paging.NowPlayingMovieSource
import com.example.movieapp.data.paging.PopularMovieSource
import com.example.movieapp.data.paging.TrendingMovieSource
import com.example.movieapp.data.remote.MovieApi
import com.example.movieapp.domain.model.MovieResult
import com.example.movieapp.domain.model.details.MovieDetails
import com.example.movieapp.domain.model.review.MovieReview
import com.example.movieapp.domain.model.search.Search
import com.example.movieapp.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow

class RemoteDataSourceImpl(
    private val movieApi: MovieApi
) : RemoteDataSource {

    override suspend fun getTrendingMovies(): Flow<PagingData<MovieResult>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                TrendingMovieSource(movieApi)
            }
        ).flow
    }

    override suspend fun getPopularMovies(): Flow<PagingData<MovieResult>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                PopularMovieSource(movieApi)
            }
        ).flow
    }

    override suspend fun getNowPlayingMovies(): Flow<PagingData<MovieResult>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                NowPlayingMovieSource(movieApi)
            }
        ).flow
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
            Log.d("TEST", "searchMovie: $result")
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}