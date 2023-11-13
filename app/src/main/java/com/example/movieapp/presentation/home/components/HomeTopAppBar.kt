package com.example.movieapp.presentation.home.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.movieapp.ui.theme.dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
    onSearchClick: () -> Unit
) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = MaterialTheme.dimens.small2,
                vertical = MaterialTheme.dimens.small1
            ),
        title = {
            Text(
                text = "My Movies",
                style = TextStyle(
                    fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        actions = {
            SearchAction(
                onSearchClick = onSearchClick
            )
        }
    )
}

@Composable
fun SearchAction(
    onSearchClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .size(MaterialTheme.dimens.medium3),
        shape = RoundedCornerShape(MaterialTheme.dimens.small1),
        color = MaterialTheme.colorScheme.outlineVariant
    ) {
        IconButton(
            onClick = onSearchClick,
            interactionSource = MutableInteractionSource()
        ) {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        }
    }
}