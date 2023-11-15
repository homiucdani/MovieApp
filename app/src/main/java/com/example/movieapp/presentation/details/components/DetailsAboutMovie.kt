package com.example.movieapp.presentation.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.movieapp.presentation.home.components.RatingBar
import com.example.movieapp.ui.theme.dimens

@Composable
fun DetailsAboutMovie(
    modifier: Modifier = Modifier,
    title: String,
    tagline: String,
    rating: Double,
    releaseDate: String,
    status: String
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.extraSmall)
    ) {
        Text(
            text = title,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.headlineSmall.fontSize
            ),
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = tagline,
            style = TextStyle(
                fontSize = MaterialTheme.typography.labelMedium.fontSize,
                fontStyle = FontStyle.Italic
            ),
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RatingBar(rating = rating)

            Spacer(modifier = Modifier.width(MaterialTheme.dimens.medium2))

            Text(
                text = releaseDate,
                style = TextStyle(
                    fontSize = MaterialTheme.typography.labelMedium.fontSize
                ),
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
            )

            Spacer(modifier = Modifier.width(MaterialTheme.dimens.medium2))

            Text(
                text = status,
                style = TextStyle(
                    fontSize = MaterialTheme.typography.labelMedium.fontSize
                ),
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
            )
        }
    }
}