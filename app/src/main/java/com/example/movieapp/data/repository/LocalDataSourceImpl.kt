package com.example.movieapp.data.repository

import com.example.movieapp.data.local.dao.SuggestionDao
import com.example.movieapp.data.mapper.toSuggestion
import com.example.movieapp.data.mapper.toSuggestionEntity
import com.example.movieapp.domain.model.Suggestion
import com.example.movieapp.domain.repository.LocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalDataSourceImpl(
    private val suggestionDao: SuggestionDao
) : LocalDataSource {

    override fun getAllSuggestions(): Flow<List<Suggestion>> {
        return suggestionDao.getAllSuggestions().map { suggestionList ->
            suggestionList.map {
                it.toSuggestion()
            }
        }
    }

    override suspend fun addSuggestion(suggestion: Suggestion) {
        suggestionDao.addSuggestion(suggestion.toSuggestionEntity())
    }

    override suspend fun deleteSuggestion(suggestionId: Int) {
        suggestionDao.deleteSuggestion(suggestionId)
    }

    override suspend fun deleteAllSuggestions() {
        suggestionDao.deleteAllSuggestions()
    }
}