package com.example.movieapp.presentation.home

sealed class HomeEvent {

    object NavigateToSearch : HomeEvent()
    data class OnMovieClick(val movieId: Int) : HomeEvent()
}
