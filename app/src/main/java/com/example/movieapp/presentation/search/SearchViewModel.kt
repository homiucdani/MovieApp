package com.example.movieapp.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.core.presentation.util.UiEvent
import com.example.movieapp.domain.model.Suggestion
import com.example.movieapp.domain.repository.LocalDataSource
import com.example.movieapp.domain.use_case.MovieUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val movieUseCases: MovieUseCases,
    private val localDataSource: LocalDataSource
) : ViewModel() {

    private val _state = MutableStateFlow(SearchState())
    val state: StateFlow<SearchState> = _state

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent: SharedFlow<UiEvent> = _uiEvent

    init {
        loadSuggestions()
    }

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.OnSearchClick -> {
                execSearch(state.value.query)
            }

            is SearchEvent.OnSearchChange -> {
                _state.update {
                    it.copy(
                        query = event.search
                    )
                }
            }

            SearchEvent.OnCloseClick -> {
                if (state.value.query.isNotBlank()) {
                    _state.update {
                        it.copy(
                            query = "",
                            showSuggestion = true
                        )
                    }
                } else {
                    viewModelScope.launch {
                        _uiEvent.emit(UiEvent.NavigateBack)
                    }
                }
            }


            is SearchEvent.OnSuggestionFocusChange -> {
                _state.update {
                    it.copy(
                        showSuggestion = event.focus
                    )
                }
            }

            is SearchEvent.OnSuggestionFocusClear -> {
                _state.update {
                    it.copy(
                        showSuggestion = false
                    )
                }
            }

            is SearchEvent.OnSearchFocusChange -> {
                _state.update {
                    it.copy(
                        showSearch = event.focus
                    )
                }
            }


            SearchEvent.OnDeleteAllSuggestions -> {
                viewModelScope.launch(Dispatchers.IO) {
                    localDataSource.deleteAllSuggestions()
                }
            }

            is SearchEvent.OnDeleteSuggestionById -> {
                viewModelScope.launch(Dispatchers.IO) {
                    localDataSource.deleteSuggestion(event.suggestionId)
                }
            }

            is SearchEvent.OnSuggestionClick -> {
                execSearch(event.suggestionName)
                _state.update {
                    it.copy(
                        query = event.suggestionName,
                        showSuggestion = false,
                        showSearch = true
                    )
                }
            }

            else -> Unit
        }
    }

    private fun execSearch(query: String) {
        _state.update {
            it.copy(
                isLoading = true
            )
        }
        viewModelScope.launch(Dispatchers.IO) {
            val result = movieUseCases.searchMovie(query)
            result.onSuccess { searchResult ->
                if (searchResult.results.isNotEmpty()) {
                    withContext(Dispatchers.Main) {
                        _state.update {
                            it.copy(
                                searchResults = searchResult.results,
                                error = null,
                                isLoading = false
                            )
                        }
                    }
                    // add suggestion
                    localDataSource.addSuggestion(
                        Suggestion(
                            id = 0,
                            suggestion = query
                        )
                    )
                } else {
                    withContext(Dispatchers.Main) {
                        _state.update {
                            it.copy(
                                searchResults = emptyList(),
                                error = "No movies found.",
                                isLoading = false
                            )
                        }
                    }
                }
            }.onFailure {
                withContext(Dispatchers.Main) {
                    _state.update {
                        it.copy(
                            error = "Check internet connection.",
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    private fun loadSuggestions() {
        viewModelScope.launch(Dispatchers.IO) {
            localDataSource.getAllSuggestions().collect { suggestionList ->
                withContext(Dispatchers.Main) {
                    _state.update {
                        it.copy(
                            suggestionResults = suggestionList
                        )
                    }
                }
            }
        }
    }
}