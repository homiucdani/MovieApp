package com.example.movieapp.di

import com.example.movieapp.domain.repository.RemoteDataSource
import com.example.movieapp.domain.use_case.GetMovieById
import com.example.movieapp.domain.use_case.GetMovieReviews
import com.example.movieapp.domain.use_case.GetNowPlayingMovies
import com.example.movieapp.domain.use_case.GetPopularMovies
import com.example.movieapp.domain.use_case.GetTrendingMovies
import com.example.movieapp.domain.use_case.MovieUseCases
import com.example.movieapp.domain.use_case.SearchMovie
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun providesMovieUseCases(remoteDataSource: RemoteDataSource): MovieUseCases {
        return MovieUseCases(
            getMovieById = GetMovieById(remoteDataSource),
            getPopularMovies = GetPopularMovies(remoteDataSource),
            getTrendingMovies = GetTrendingMovies(remoteDataSource),
            getMovieReviews = GetMovieReviews(remoteDataSource),
            searchMovie = SearchMovie(remoteDataSource),
            getNowPlayingMovies = GetNowPlayingMovies(remoteDataSource)
        )
    }
}