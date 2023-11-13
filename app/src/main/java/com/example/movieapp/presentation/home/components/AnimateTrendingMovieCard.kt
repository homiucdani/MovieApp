package com.example.movieapp.presentation.home.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapp.R
import com.example.movieapp.core.domain.util.Constants
import com.example.movieapp.ui.theme.dimens

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AnimateTrendingMovieCard(
    state: PagerState,
    imagePath: String
) {
    val image = remember(imagePath) {
        "${Constants.MOVIE_IMAGE_ORIGINAL_BASE_URL}$imagePath"
    }

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
        TrendingMovieCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(MaterialTheme.dimens.trendingMovieCardHeight)
                .graphicsLayer {
                    scaleY = imageSize
                },
            image = image
        )
    }
}

@Composable
fun TrendingMovieCard(
    modifier: Modifier = Modifier,
    image: String
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
            contentScale = ContentScale.Crop
        )
    }
}