package com.example.movieapp.presentation.search

sealed class SearchEvent {

    data class OnSearchClick(val query: String) : SearchEvent()

}
