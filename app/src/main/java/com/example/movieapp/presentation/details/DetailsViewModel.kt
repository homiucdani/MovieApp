package com.example.movieapp.presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.use_case.MovieUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCases: MovieUseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(DetailsState())
    val state: StateFlow<DetailsState> = _state

    init {
        getMovieById()
    }

    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.OnTabSelected -> {
                _state.update {
                    it.copy(
                        selectedTab = event.tabIndex
                    )
                }
            }

            is DetailsEvent.OnToggleReview -> {
                _state.update {
                    it.copy(
                        movieUiState = it.movieUiState.map { movieUiState ->
                            if (movieUiState.reviews == event.movieReviewResult) {
                                movieUiState.copy(
                                    expanded = !movieUiState.expanded
                                )
                            } else {
                                movieUiState
                            }
                        }
                    )
                }
            }

            else -> Unit
        }
    }

    private fun getReviews(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val reviews = async { useCases.getMovieReviews(movieId) }.await()
            withContext(Dispatchers.Main) {

                val data =
                    reviews.getOrNull()?.results?.filter { it.authorDetails.avatarPath != null }
                        ?.take(5) ?: emptyList()

                if (reviews.isSuccess) {
                    _state.update {
                        it.copy(
                            movieUiState = data.map {movieResult ->
                                DetailsMovieUiState(
                                    reviews = movieResult
                                )
                            }
                        )
                    }
                } else {
                    _state.update {
                        it.copy(
                            error = reviews.exceptionOrNull()?.localizedMessage
                        )
                    }
                }
            }
        }
    }

    private fun getMovieById() {
        val movieId = savedStateHandle.get<Int>("movie_id")

        if (movieId != null) {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            viewModelScope.launch(Dispatchers.IO) {
                val result = useCases.getMovieById(movieId)
                if (result.isSuccess) {
                    _state.update {
                        it.copy(
                            movieDetails = result.getOrNull(),
                            isLoading = false
                        )
                    }
                } else {
                    _state.update {
                        it.copy(
                            error = result.exceptionOrNull()?.localizedMessage,
                            isLoading = false
                        )
                    }
                }
            }
            // async
            getReviews(movieId)
        }
    }
}