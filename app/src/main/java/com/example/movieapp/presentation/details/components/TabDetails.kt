package com.example.movieapp.presentation.details.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.example.movieapp.domain.model.review.MovieReviewResult
import com.example.movieapp.presentation.details.DetailsMovieUiState
import com.example.movieapp.ui.theme.dimens

@Composable
fun TabDetails(
    overview: String,
    listOfMovieUiState: List<DetailsMovieUiState>,
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
    onToggleReview: (MovieReviewResult) -> Unit
) {

    val categories = rememberSaveable {
        mutableStateOf(listOf("Description", "Reviews"))
    }

    LazyColumn {
        item {
            TabRow(
                selectedTabIndex = selectedTab,
                tabs = {
                    categories.value.forEachIndexed { index, category ->
                        Tab(
                            selected = selectedTab == index,
                            onClick = {
                                onTabSelected(index)
                            },
                            text = {
                                Text(text = category)
                            },
                            interactionSource = MutableInteractionSource(),
                        )
                    }
                }
            )
        }

        when (selectedTab) {
            0 -> {
                item {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                vertical = MaterialTheme.dimens.medium1,
                                horizontal = MaterialTheme.dimens.small3
                            ),
                        text = overview
                    )
                }
            }

            1 -> {
                items(listOfMovieUiState) { movieUiState ->
                    ReviewItem(
                        movieReviewResult = movieUiState.movieReview,
                        onToggleReview = { movieReviewResult ->
                            onToggleReview(movieReviewResult)
                        },
                        expanded = movieUiState.expanded
                    )
                }
            }
        }
    }
}