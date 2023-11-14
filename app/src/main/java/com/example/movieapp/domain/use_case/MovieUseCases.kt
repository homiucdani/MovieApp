package com.example.movieapp.domain.use_case

data class MovieUseCases(
    val getMovieById: GetMovieById,
    val getPopularMovies: GetPopularMovies,
    val getTrendingMovies: GetTrendingMovies,
    val getNowPlayingMovies: GetNowPlayingMovies,
    val getMovieReviews: GetMovieReviews,
    val searchMovie: SearchMovie
)
