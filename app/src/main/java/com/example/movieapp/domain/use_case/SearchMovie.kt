package com.example.movieapp.domain.use_case

import com.example.movieapp.domain.model.search.Search
import com.example.movieapp.domain.repository.RemoteDataSource

class SearchMovie(
    private val remoteDataSource: RemoteDataSource
) {

    suspend operator fun invoke(query: String): Result<Search> {
        return remoteDataSource.searchMovie(query)
    }
}