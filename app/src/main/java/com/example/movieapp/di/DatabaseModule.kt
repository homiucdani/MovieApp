package com.example.movieapp.di

import android.content.Context
import androidx.room.Room
import com.example.movieapp.core.domain.util.Constants
import com.example.movieapp.data.local.MovieDatabase
import com.example.movieapp.data.local.dao.SuggestionDao
import com.example.movieapp.data.repository.LocalDataSourceImpl
import com.example.movieapp.domain.repository.LocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Provides
    @Singleton
    fun providesMovieDatabase(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            Constants.MOVIE_DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providesSuggestionDao(movieDatabase: MovieDatabase):SuggestionDao {
        return movieDatabase.suggestionDao()
    }

    @Provides
    @Singleton
    fun providesLocalDataSource(suggestionDao: SuggestionDao): LocalDataSource {
        return LocalDataSourceImpl(suggestionDao)
    }

}