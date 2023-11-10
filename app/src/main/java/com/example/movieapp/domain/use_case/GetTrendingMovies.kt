package com.example.movieapp.domain.use_case

import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.repository.RemoteDataSource

class GetTrendingMovies(
    private val remoteDataSource: RemoteDataSource
) {
    suspend operator fun invoke(): Result<Movie> {
        return remoteDataSource.getTrendingMovies()
    }
}