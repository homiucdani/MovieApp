package com.example.movieapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieapp.data.local.dao.SuggestionDao
import com.example.movieapp.data.local.entity.SuggestionEntity

@Database(
    entities = [
        SuggestionEntity::class
    ],
    version = 1
)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun suggestionDao(): SuggestionDao
}