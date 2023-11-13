package com.example.movieapp.domain.use_case

import androidx.paging.PagingData
import com.example.movieapp.domain.model.MovieResult
import com.example.movieapp.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow

class GetTrendingMovies(
    private val remoteDataSource: RemoteDataSource
) {
    suspend operator fun invoke(): Flow<PagingData<MovieResult>> {
        return remoteDataSource.getTrendingMovies()
    }
}