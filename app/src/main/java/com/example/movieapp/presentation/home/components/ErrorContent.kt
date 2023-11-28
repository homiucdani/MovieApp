package com.example.movieapp.presentation.home.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import com.example.movieapp.ui.theme.MovieAppTheme
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

@Composable
fun ErrorContent(
    modifier: Modifier = Modifier,
    error: LoadState.Error? = null
) {

    var startAnimation by remember {
        mutableStateOf(false)
    }

    val fadeInAnimation by animateFloatAsState(
        targetValue = if (startAnimation) 0.5f else 0f,
        label = "",
        animationSpec = tween(
            durationMillis = 1000
        )
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
    }

    val message = remember {
        mutableStateOf(
            when (error?.error) {
                is UnknownHostException -> {
                    "No internet connection."
                }
                is SocketTimeoutException -> {
                    "Check your internet connection."
                }

                else -> "Unknown error."
            }
        )
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.alpha(fadeInAnimation),
            text = message.value,
            style = TextStyle(
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                fontWeight = FontWeight.SemiBold
            ),
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview
@Composable
fun ErrorContentPrev() {
    MovieAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface)
        ) {
            ErrorContent(
                modifier = Modifier.fillMaxSize(),
                error = LoadState.Error(ConnectException())
            )
        }
    }
}