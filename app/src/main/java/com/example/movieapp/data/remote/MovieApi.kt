package com.example.movieapp.data.remote

import com.example.movieapp.data.remote.dto.MovieDto
import com.example.movieapp.data.remote.dto.MovieResultDto
import com.example.movieapp.util.Constants
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

    @GET("movie/{movie_id}")
    suspend fun getMovieById(
        @Path("movie_id") movieId: String,
        @Query("api_key") api: String = Constants.API_KEY
    ): MovieResultDto

}