package com.example.movieapp.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.use_case.MovieUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.ConnectException
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val movieUseCases: MovieUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(SearchState())
    val state: StateFlow<SearchState> = _state

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

            else -> Unit
        }
    }

    private fun execSearch(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = movieUseCases.searchMovie(query)
            result.onSuccess { searchResult ->
                withContext(Dispatchers.Main) {
                    _state.update {
                        it.copy(
                            searchResults = searchResult.results
                        )
                    }
                }
            }.onFailure { error ->
                withContext(Dispatchers.Main) {
                    when (error) {
                        is ConnectException -> {
                            _state.update {
                                it.copy(
                                    error = "No Internet Connection"
                                )
                            }
                        }
                        else -> _state.update { it.copy(error = "No movies found.") }
                    }
                }
            }
        }
    }
}