package com.example.movieapp.presentation.search.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapp.R
import com.example.movieapp.core.domain.util.Constants
import com.example.movieapp.domain.model.search.SearchResult
import com.example.movieapp.ui.theme.dimens

@Composable
fun SearchCard(
    modifier: Modifier = Modifier,
    searchResult: SearchResult
) {

    val context = LocalContext.current
    val image = "${Constants.MOVIE_IMAGE_W500_BASE_URL}${searchResult.posterPath}"

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Top
    ) {
        Surface(
            modifier = Modifier
                .weight(0.2f)
                .height(MaterialTheme.dimens.searchCardHeight),
            shape = RoundedCornerShape(MaterialTheme.dimens.small2)
        ) {
            AsyncImage(
                model = ImageRequest
                    .Builder(context)
                    .error(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .data(image)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
        }

        Column(
            modifier = Modifier
                .weight(0.8f)
                .padding(
                    horizontal = MaterialTheme.dimens.small1,
                    vertical = MaterialTheme.dimens.extraSmall
                )
        ) {
            Text(
                text = "This is a title",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "This is a simple description about the movie",
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    fontSize = MaterialTheme.typography.labelLarge.fontSize
                ),
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
    }
}
