package com.example.movieapp.presentation.search


sealed class SearchEvent {

    data class OnSearchChange(val search: String) : SearchEvent()
    object OnSearchClick : SearchEvent()
    object OnCloseClick : SearchEvent()

    data class OnSuggestionFocusChange(val focus: Boolean) : SearchEvent()
    object OnSuggestionFocusClear : SearchEvent()

    data class OnSearchFocusChange(val focus: Boolean) : SearchEvent()

    object OnDeleteAllSuggestions : SearchEvent()

    data class OnDeleteSuggestionById(val suggestionId: Int) : SearchEvent()

    data class NavigateToMovieDetails(val movieId: Int) : SearchEvent()

    data class OnSuggestionClick(val suggestionName: String):SearchEvent()
}
