package com.example.movieapp.domain.use_case

import com.example.movieapp.domain.model.review.MovieReview
import com.example.movieapp.domain.repository.RemoteDataSource

class GetMovieReviews(
    private val remoteDataSource: RemoteDataSource
) {
    suspend operator fun invoke(movieId: Int): Result<MovieReview> {
        return remoteDataSource.getMovieReviews(movieId = movieId)
    }
}