package com.example.movieapp.domain.repository

import com.example.movieapp.domain.model.Suggestion
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    fun getAllSuggestions(): Flow<List<Suggestion>>

    suspend fun addSuggestion(suggestion: Suggestion)

    suspend fun deleteSuggestion(suggestionId: Int)

    suspend fun deleteAllSuggestions()
}