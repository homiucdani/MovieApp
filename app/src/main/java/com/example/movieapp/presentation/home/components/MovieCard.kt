package com.example.movieapp.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapp.R
import com.example.movieapp.core.domain.util.Constants
import com.example.movieapp.domain.model.MovieResult
import com.example.movieapp.ui.theme.dimens

@Composable
fun MovieCard(
    movieCardTitle: String,
    movies: LazyPagingItems<MovieResult>
) {
    Column(
        modifier = Modifier.padding(top = MaterialTheme.dimens.medium2),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.small2)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = MaterialTheme.dimens.medium2),
            text = movieCardTitle,
            style = TextStyle(
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.Bold
            ),
            color = MaterialTheme.colorScheme.onSurface
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = MaterialTheme.dimens.small3),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.small2)
        ) {
            items(
                count = movies.itemCount
            ) { index ->
                val movie = movies[index]
                movie?.let { movieResult ->
                    VerticalMovieCard(movieResult = movieResult)
                }
            }
        }
    }
}

@Composable
fun VerticalMovieCard(
    movieResult: MovieResult
) {

    val context = LocalContext.current

    val image = "${Constants.MOVIE_IMAGE_ORIGINAL_BASE_URL}${movieResult.posterPath}"

    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.extraSmall)
    ) {

        Surface(
            modifier = Modifier.height(MaterialTheme.dimens.popularMovieCardHeight),
            shape = RoundedCornerShape(MaterialTheme.dimens.small2),
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .error(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .crossfade(true)
                    .data(image)
                    .build(),
                contentDescription = null
            )
        }

        Text(
            text = if (movieResult.title.length > 14) "${movieResult.title.take(20)}..." else movieResult.title,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.Bold
            ),
            color = MaterialTheme.colorScheme.onSurface,
        )

        RatingBar(rating = movieResult.voteAverage.div(2))
    }
}