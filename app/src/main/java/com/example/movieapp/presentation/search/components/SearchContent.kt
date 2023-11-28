package com.example.movieapp.presentation.search.components


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import com.example.movieapp.domain.model.Suggestion
import com.example.movieapp.domain.model.search.SearchResult
import com.example.movieapp.ui.theme.dimens

@Composable
fun SearchContent(
    modifier: Modifier = Modifier,
    search: String,
    onSearchChange: (String) -> Unit,
    error: String?,
    searchResults: List<SearchResult>,
    suggestionResults: List<Suggestion>,
    onSearchClick: () -> Unit,
    showSearch: Boolean,
    showSuggestion: Boolean,
    onSuggestionFocusChange: (Boolean) -> Unit,
    onSearchFocusChange: (Boolean) -> Unit,
    onSuggestionClearFocus: () -> Unit,
    onCloseClick: () -> Unit,
    onDeleteAllSuggestions: () -> Unit,
    onDeleteSuggestionById: (Int) -> Unit,
    onSearchedMovieClick: (Int) -> Unit,
    onSuggestionClick: (String) -> Unit
) {

    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    onSuggestionFocusChange(it.isFocused)
                },
            value = search,
            onValueChange = onSearchChange,
            leadingIcon = {
                IconButton(
                    onClick = {
                        onSearchClick()
                        onSuggestionClearFocus()
                        onSearchFocusChange(true)
                    }
                ) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = onCloseClick
                ) {
                    Icon(imageVector = Icons.Filled.Clear, contentDescription = null)
                }
            },
            shape = RoundedCornerShape(MaterialTheme.dimens.small1)
        )

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.small3))

        AnimatedVisibility(visible = showSuggestion || showSearch) {

            when {
                showSuggestion -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.small2)
                    ) {

                        items(suggestionResults.take(10)) { suggestion ->
                            SuggestionItem(
                                suggestion = suggestion,
                                onSuggestionClick = onSuggestionClick,
                                onDeleteSuggestion = onDeleteSuggestionById
                            )
                        }

                        if (suggestionResults.isNotEmpty()) {
                            item {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    OutlinedButton(
                                        onClick = onDeleteAllSuggestions
                                    ) {
                                        Text(text = "Delete all")
                                    }
                                }
                            }
                        }
                    }
                }

                showSearch -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.small2)
                    ) {
                        items(searchResults.take(10)) { searchResult ->
                            SearchCard(
                                modifier = Modifier.clickable(
                                    indication = null,
                                    interactionSource = MutableInteractionSource()
                                ) {
                                    onSearchedMovieClick(searchResult.id)
                                },
                                searchResult = searchResult
                            )
                        }
                    }
                }
            }
        }

        if (!error.isNullOrEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = error)
            }
        }
    }
}