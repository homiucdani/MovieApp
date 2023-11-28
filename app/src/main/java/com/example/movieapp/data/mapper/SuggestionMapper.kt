package com.example.movieapp.data.mapper

import com.example.movieapp.data.local.entity.SuggestionEntity
import com.example.movieapp.domain.model.Suggestion

fun Suggestion.toSuggestionEntity(): SuggestionEntity {
    return SuggestionEntity(
        id,
        suggestion
    )
}

fun SuggestionEntity.toSuggestion(): Suggestion {
    return Suggestion(
        id,
        suggestion
    )
}