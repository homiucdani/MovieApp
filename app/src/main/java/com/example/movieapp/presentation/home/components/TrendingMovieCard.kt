package com.example.movieapp.presentation.home.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapp.R
import com.example.movieapp.core.domain.util.Constants
import com.example.movieapp.domain.model.MovieResult
import com.example.movieapp.ui.theme.dimens


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TrendingMovieCard(
    pagerState: PagerState,
    trendingMovies: LazyPagingItems<MovieResult>,
    onCardClick: (Int) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.small2)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = MaterialTheme.dimens.medium2),
            text = stringResource(id = R.string.trending),
            style = TextStyle(
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.Bold
            ),
            color = MaterialTheme.colorScheme.onSurface
        )
        AnimateTrendingMovieCard(
            state = pagerState,
            trendingMovies = trendingMovies,
            onCardClick = onCardClick
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AnimateTrendingMovieCard(
    state: PagerState,
    trendingMovies: LazyPagingItems<MovieResult>,
    onCardClick: (Int) -> Unit
) {

    HorizontalPager(
        state = state,
        contentPadding = PaddingValues(horizontal = MaterialTheme.dimens.medium3),
        pageSpacing = MaterialTheme.dimens.small2
    ) { index ->
        val pageOffset = (state.currentPage - index) + state.currentPageOffsetFraction
        val imageSize by animateFloatAsState(
            targetValue = if (pageOffset != 0.0f) 0.65f else 1f,
            label = ""
        )
        TrendingMovieItem(
            modifier = Modifier
                .fillMaxWidth()
                .height(MaterialTheme.dimens.trendingMovieCardHeight)
                .graphicsLayer {
                    scaleY = imageSize
                }
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null
                ) {
                    trendingMovies[index]?.let { movieResult ->
                        onCardClick(movieResult.id)
                    }
                },
            image = "${Constants.MOVIE_IMAGE_ORIGINAL_BASE_URL}${trendingMovies[index]?.posterPath}"
        )
    }
}

@Composable
fun TrendingMovieItem(
    modifier: Modifier = Modifier,
    image: String?
) {
    val context = LocalContext.current
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(MaterialTheme.dimens.small2)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(
                context
            )
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .data(image)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
    }
}