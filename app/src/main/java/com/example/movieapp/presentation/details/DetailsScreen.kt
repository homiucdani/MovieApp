package com.example.movieapp.presentation.details

import android.annotation.SuppressLint
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.example.movieapp.core.domain.util.Constants
import com.example.movieapp.presentation.details.components.DetailsAboutMovie
import com.example.movieapp.presentation.details.components.TabDetails
import com.example.movieapp.ui.theme.dimens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsScreen(
    state: DetailsState,
    onEvent: (DetailsEvent) -> Unit
) {

    val image = "${Constants.MOVIE_IMAGE_ORIGINAL_BASE_URL}${state.movieDetails?.posterPath}"

    if (state.isLoading && state.movieDetails != null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary
            )
        }
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
                        shape = RoundedCornerShape(MaterialTheme.dimens.medium3)
                    ) {
                        AsyncImage(
                            modifier = Modifier.fillMaxSize(),
                            model = image,
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
                                onEvent(DetailsEvent.OnBackClick)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurface
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
        } ?: Box(modifier = Modifier.fillMaxSize()) { Text(text = "Something went wrong.") }
    }
}