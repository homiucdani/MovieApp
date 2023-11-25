package com.example.movieapp.presentation.search

sealed class SearchEvent {

    data class OnSearchChange(val search: String) : SearchEvent()
    object OnSearchClick : SearchEvent()

}
