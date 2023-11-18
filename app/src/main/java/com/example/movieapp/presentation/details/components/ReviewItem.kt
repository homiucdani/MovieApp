package com.example.movieapp.presentation.details.components

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapp.R
import com.example.movieapp.core.domain.util.Constants
import com.example.movieapp.domain.model.review.MovieReviewResult
import com.example.movieapp.presentation.home.components.RatingBar
import com.example.movieapp.ui.theme.dimens

@Composable
fun ReviewItem(
    movieReviewResult: MovieReviewResult,
    expanded: Boolean,
    onToggleReview: (MovieReviewResult) -> Unit
) {

    val context = LocalContext.current
    val avatar =
        "${Constants.MOVIE_IMAGE_ORIGINAL_BASE_URL}${movieReviewResult.authorDetails.avatarPath}"


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.dimens.small1)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onToggleReview(movieReviewResult)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            AvatarImage(context, avatar)

            ReviewDetails(
                movieReviewResult = movieReviewResult,
                expanded = expanded
            )
        }

        AnimatedVisibility(visible = expanded) {
            ReviewExpandedDetails(
                reviewContent = movieReviewResult.content,
                createdAt = movieReviewResult.createdAt,
                updatedAt = movieReviewResult.updatedAt
            )
        }
    }
}

@Composable
private fun ReviewExpandedDetails(
    reviewContent: String,
    createdAt: String,
    updatedAt: String
) {
    val reviewCreated = createdAt.split("T")
    val reviewUpdated = updatedAt.split("T")

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = reviewContent,
            style = TextStyle(
                fontWeight = FontWeight.Light,
                fontSize = MaterialTheme.typography.labelMedium.fontSize
            ),
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.small1))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Created at: ${reviewCreated[0]}")
            Text(text = "Updated at: ${reviewUpdated[0]}")
        }
    }
}

@Composable
private fun ReviewDetails(
    movieReviewResult: MovieReviewResult,
    expanded: Boolean
) {
    Column(
        modifier = Modifier
            .padding(horizontal = MaterialTheme.dimens.small1)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = movieReviewResult.author,
                style = TextStyle(
                    fontWeight = FontWeight.Medium,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize
                ),
                color = MaterialTheme.colorScheme.onSurface
            )

            Icon(
                imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = null
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${movieReviewResult.authorDetails.rating.div(2)}",
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = MaterialTheme.typography.labelMedium.fontSize
                ),
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.width(MaterialTheme.dimens.small1))

            RatingBar(rating = movieReviewResult.authorDetails.rating)
        }
    }
}

@Composable
private fun AvatarImage(context: Context, avatar: String) {
    Surface(
        modifier = Modifier
            .size(MaterialTheme.dimens.large)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = CircleShape
            )
            .padding(MaterialTheme.dimens.extraSmall),
        shape = CircleShape
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = ImageRequest.Builder(context)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .data(avatar)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
    }
}