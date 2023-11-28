package com.example.movieapp.presentation.search

import com.example.movieapp.domain.model.Suggestion
import com.example.movieapp.domain.model.search.SearchResult

data class SearchState(
    val query: String = "",
    val searchResults: List<SearchResult> = emptyList(),
    val suggestionResults: List<Suggestion> = emptyList(),
    val showSearch: Boolean = false,
    val showSuggestion: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)
