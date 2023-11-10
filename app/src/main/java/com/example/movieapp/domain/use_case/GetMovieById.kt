package com.example.movieapp.domain.use_case

import com.example.movieapp.domain.model.details.MovieDetails
import com.example.movieapp.domain.repository.RemoteDataSource

class GetMovieById(
    private val remoteDataSource: RemoteDataSource
) {

    suspend operator fun invoke(movieId: Int): Result<MovieDetails> {
        return remoteDataSource.getMovieById(movieId)
    }
}