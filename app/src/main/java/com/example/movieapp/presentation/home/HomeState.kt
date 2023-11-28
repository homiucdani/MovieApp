package com.example.movieapp.presentation.home

import androidx.paging.PagingData
import com.example.movieapp.connectivity.ConnectivityObserver
import com.example.movieapp.domain.model.MovieResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class HomeState(
    val trendingMovies: Flow<PagingData<MovieResult>> = emptyFlow(),
    val popularMovies: Flow<PagingData<MovieResult>> = emptyFlow(),
    val nowPlayingMovies: Flow<PagingData<MovieResult>> = emptyFlow(),
    val status: ConnectivityObserver.Status = ConnectivityObserver.Status.Unavailable
)
