package com.example.movieapp.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.movieapp.core.domain.util.Constants

@Entity(
    tableName = Constants.MOVIE_SUGGESTION_TABLE,
    indices = arrayOf(Index("suggestion", unique = true)) // dont allow text duplicates again in db
)
data class SuggestionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val suggestion: String
)
