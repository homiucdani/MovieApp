package com.example.movieapp.connectivity

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {

    fun observeNetwork(): Flow<ConnectivityObserver.Status>

    enum class Status {
        Available, Unavailable, Losing, Lost
    }
}