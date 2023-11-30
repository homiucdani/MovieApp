package com.example.movieapp.presentation.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapp.R
import com.example.movieapp.core.domain.util.Constants
import com.example.movieapp.presentation.details.components.DetailsAboutMovie
import com.example.movieapp.presentation.details.components.TabDetails
import com.example.movieapp.ui.theme.dimens

@Composable
fun DetailsScreen(
    state: DetailsState,
    onEvent: (DetailsEvent) -> Unit
) {

    val image = "${Constants.MOVIE_IMAGE_ORIGINAL_BASE_URL}${state.movieDetails?.posterPath}"
    val context = LocalContext.current
    var canClick by remember {
        mutableStateOf(true)
    }

    if (state.isLoading || state.error != null) {
        LoadOrErrorContent(
            state,
            onBackPressed = {
                canClick = false
                onEvent(DetailsEvent.OnBackClick)
            },
            canClick = canClick
        )
    } else {
        state.movieDetails?.let { movieDetails ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f)
                ) {

                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        shape = RoundedCornerShape(
                            bottomEnd = MaterialTheme.dimens.medium3,
                            bottomStart = MaterialTheme.dimens.medium3
                        )
                    ) {
                        AsyncImage(
                            modifier = Modifier.fillMaxSize(),
                            model = ImageRequest.Builder(context)
                                .error(R.drawable.placeholder)
                                .placeholder(R.drawable.placeholder)
                                .crossfade(true)
                                .data(image)
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(MaterialTheme.dimens.small2)
                    ) {
                        IconButton(
                            onClick = {
                                if (canClick) {
                                    canClick = false
                                    onEvent(DetailsEvent.OnBackClick)
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(MaterialTheme.dimens.medium1))

                DetailsAboutMovie(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = MaterialTheme.dimens.medium2),
                    title = movieDetails.title,
                    tagline = movieDetails.tagline,
                    rating = movieDetails.voteAverage,
                    releaseDate = movieDetails.releaseDate,
                    status = movieDetails.status
                )

                Spacer(modifier = Modifier.height(MaterialTheme.dimens.medium1))

                TabDetails(
                    selectedTab = state.selectedTab,
                    onTabSelected = { index ->
                        onEvent(DetailsEvent.OnTabSelected(index))
                    },
                    overview = movieDetails.overview,
                    listOfMovieUiState = state.movieUiState,
                    onToggleReview = { movieReviewResult ->
                        onEvent(DetailsEvent.OnToggleReview(movieReviewResult))
                    }
                )
            }
        } ?: EmptyMovieContent(
            error = "Something went wrong",
            onBackPressed = {
                onEvent(DetailsEvent.OnBackClick)
            }
        )
    }
}

@Composable
fun EmptyMovieContent(error: String, onBackPressed: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        IconButton(
            modifier = Modifier.align(Alignment.TopStart),
            onClick = onBackPressed
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
        }
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = error,
            style = TextStyle(
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                fontWeight = FontWeight.SemiBold
            ),
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun LoadOrErrorContent(
    state: DetailsState,
    canClick: Boolean,
    onBackPressed: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (state.error != null) {
            IconButton(
                modifier = Modifier.align(Alignment.TopStart),
                onClick = {
                    if (canClick) {
                        onBackPressed()
                    }
                }
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = state.error,
                style = TextStyle(
                    fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                    fontWeight = FontWeight.SemiBold
                ),
                color = MaterialTheme.colorScheme.onSurface
            )
        } else {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}