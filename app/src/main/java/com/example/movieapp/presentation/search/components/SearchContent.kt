package com.example.movieapp.presentation.search.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.movieapp.domain.model.search.SearchResult
import com.example.movieapp.ui.theme.dimens

@Composable
fun SearchContent(
    modifier: Modifier = Modifier,
    search: String,
    onSearchChange: (String) -> Unit,
    searchResults: List<SearchResult>,
    onSearchClick: () -> Unit,
    onCloseClick: () -> Unit
) {

    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = search,
            onValueChange = onSearchChange,
            leadingIcon = {
                IconButton(
                    onClick = onSearchClick
                ) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = onCloseClick
                ) {
                    Icon(imageVector = Icons.Filled.Clear, contentDescription = null)
                }
            },
            shape = RoundedCornerShape(MaterialTheme.dimens.small1)
        )

        Spacer(modifier = Modifier.height(MaterialTheme.dimens.small3))

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(searchResults) { searchResult ->
                SearchCard(searchResult = searchResult)
            }
        }
    }
}