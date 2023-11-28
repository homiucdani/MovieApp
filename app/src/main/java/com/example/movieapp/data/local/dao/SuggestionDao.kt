package com.example.movieapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.movieapp.data.local.entity.SuggestionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SuggestionDao {

    @Query("SELECT * FROM suggestion_table")
    fun getAllSuggestions(): Flow<List<SuggestionEntity>>

    @Upsert
    suspend fun addSuggestion(suggestionEntity: SuggestionEntity)

    @Query("DELETE FROM suggestion_table WHERE id = :suggestionId")
    suspend fun deleteSuggestion(suggestionId: Int)

    @Query("DELETE FROM suggestion_table")
    suspend fun deleteAllSuggestions()

}