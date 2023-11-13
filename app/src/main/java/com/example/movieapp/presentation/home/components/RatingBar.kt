package com.example.movieapp.presentation.home.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.movieapp.ui.theme.dimens

@Composable
fun RatingBar(
    rating: Double,
    stars: Int = 5,
    color: Color = MaterialTheme.colorScheme.primary,
    unfilledColor: Color = MaterialTheme.colorScheme.secondary
) {

    Row {
        for (index in 1..stars) {
            Icon(
                modifier = Modifier.size(MaterialTheme.dimens.small2),
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = if (index <= rating) color else unfilledColor
            )
        }
    }
}