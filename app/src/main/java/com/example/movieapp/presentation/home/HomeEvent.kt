package com.example.movieapp.presentation.home

sealed class HomeEvent {

    data class OnMovieClick(val movieId: Int) : HomeEvent()
}
