package com.example.movieapp.data.remote

import com.example.movieapp.core.domain.util.Constants
import com.example.movieapp.data.remote.dto.MovieDto
import com.example.movieapp.data.remote.dto.details.MovieDetailsDto
import com.example.movieapp.data.remote.dto.review.MovieReviewDto
import com.example.movieapp.data.remote.dto.search.SearchDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/now_playing")
    suspend fun getTrendingMovies(
        @Query("api_key") api: String = Constants.API_KEY,
        @Query("page") page: Int = 1
    ): MovieDto

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") api: String = Constants.API_KEY,
        @Query("page") page: Int = 1
    ): MovieDto

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") api: String = Constants.API_KEY,
        @Query("page") page: Int = 1
    ): MovieDto

    @GET("movie/{movie_id}")
    suspend fun getMovieById(
        @Path("movie_id") movieId: Int,
        @Query("api_key") api: String = Constants.API_KEY
    ): MovieDetailsDto

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: Int,
        @Query("language") lang: String = "en",
        @Query("page") page: Int = 1,
        @Query("api_key") api: String = Constants.API_KEY
    ): MovieReviewDto

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") query: String,
        @Query("api_key") api: String = Constants.API_KEY
    ): SearchDto

}