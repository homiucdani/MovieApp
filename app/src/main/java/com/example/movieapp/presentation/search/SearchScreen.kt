package com.example.movieapp.presentation.search

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.movieapp.presentation.search.components.SearchContent
import com.example.movieapp.ui.theme.dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    state: SearchState,
    onEvent: (SearchEvent) -> Unit
) {
    var isButtonEnabled by remember {
        mutableStateOf(true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Search",
                        style = TextStyle(
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            fontWeight = FontWeight.Bold
                        ),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            )
        }
    ) { paddingValues ->
        SearchContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = MaterialTheme.dimens.small2),
            search = state.query,
            onSearchChange = {
                onEvent(SearchEvent.OnSearchChange(it))
            },
            searchResults = state.searchResults,
            onSearchClick = {
                onEvent(SearchEvent.OnSearchClick)
            },
            error = state.error,
            onCloseClick = {
                onEvent(SearchEvent.OnCloseClick)
            },
            suggestionResults = state.suggestionResults,
            onSuggestionFocusChange = { focusState ->
                onEvent(SearchEvent.OnSuggestionFocusChange(focusState))
            },
            onSearchFocusChange = {
                onEvent(SearchEvent.OnSearchFocusChange(it))
            },
            showSearch = state.showSearch,
            showSuggestion = state.showSuggestion,
            onSuggestionClearFocus = {
                onEvent(SearchEvent.OnSuggestionFocusClear)
            },
            onDeleteAllSuggestions = {
                onEvent(SearchEvent.OnDeleteAllSuggestions)
            },
            onDeleteSuggestionById = { suggestionId ->
                onEvent(SearchEvent.OnDeleteSuggestionById(suggestionId))
            },
            onSearchedMovieClick = { movieId ->
                onEvent(SearchEvent.NavigateToMovieDetails(movieId))
            },
            onSuggestionClick = { suggestion ->
                onEvent(SearchEvent.OnSuggestionClick(suggestion))
            },
            onButtonEnabled = isButtonEnabled,
            onChangeButtonEnable = { unableButton ->
                isButtonEnabled = unableButton
            }
        )
    }
}