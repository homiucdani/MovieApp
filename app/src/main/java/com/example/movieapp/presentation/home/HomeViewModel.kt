package com.example.movieapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.connectivity.ConnectivityObserver
import com.example.movieapp.domain.use_case.MovieUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: MovieUseCases,
    private val connectivityObserver: ConnectivityObserver
) : ViewModel() {


    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    init {
        observeNetwork()
    }

    private fun loadDataAsync() {
        viewModelScope.launch(Dispatchers.IO) {
            val trendingMovies = async { useCases.getTrendingMovies() }.await()
            val popularMovies = async { useCases.getPopularMovies() }.await()
            val nowPlayingMovies = async { useCases.getNowPlayingMovies() }.await()

            withContext(Dispatchers.Main) {
                _state.update {
                    it.copy(
                        trendingMovies = trendingMovies,
                        popularMovies = popularMovies,
                        nowPlayingMovies = nowPlayingMovies
                    )
                }
            }
        }
    }

    private fun observeNetwork() {
        viewModelScope.launch {
            connectivityObserver.observeNetwork().collectLatest { status ->
                if (status == ConnectivityObserver.Status.Available) {
                    loadDataAsync()
                }

                if (status == ConnectivityObserver.Status.Lost) {
                    _state.value = HomeState()
                }
                _state.update {
                    it.copy(
                        status = status
                    )
                }
            }
        }
    }
}