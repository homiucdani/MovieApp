package com.example.movieapp.presentation.search.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.movieapp.domain.model.Suggestion

@Composable
fun SuggestionItem(
    suggestion: Suggestion,
    onSuggestionClick: (String) -> Unit,
    onDeleteSuggestion: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onSuggestionClick(suggestion.suggestion)
            },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = suggestion.suggestion)
        IconButton(
            onClick = {
                onDeleteSuggestion(suggestion.id)
            }
        ) {
            Icon(imageVector = Icons.Default.Clear, contentDescription = null)
        }
    }
}