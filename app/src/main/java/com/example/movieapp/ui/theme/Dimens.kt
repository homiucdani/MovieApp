package com.example.movieapp.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimens(
    val extraSmall: Dp = 0.dp,
    val small1: Dp = 0.dp,
    val small2: Dp = 0.dp,
    val small3: Dp = 0.dp,
    val medium1: Dp = 0.dp,
    val medium2: Dp = 0.dp,
    val medium3: Dp = 0.dp,
    val large: Dp = 0.dp,
    val trendingMovieCardHeight: Dp = 200.dp,
    val popularMovieCardHeight: Dp = 200.dp
)

val CompactSmallDimens = Dimens(
    extraSmall = 1.dp,
    small1 = 6.dp,
    small2 = 5.dp,
    small3 = 8.dp,
    medium1 = 15.dp,
    medium2 = 26.dp,
    medium3 = 30.dp,
    large = 45.dp,
    trendingMovieCardHeight = 100.dp,
    popularMovieCardHeight = 100.dp
)

val CompactMediumDimens = Dimens(
    extraSmall = 3.dp,
    small1 = 9.dp,
    small2 = 13.dp,
    small3 = 17.dp,
    medium1 = 22.dp,
    medium2 = 28.dp,
    medium3 = 35.dp,
    large = 65.dp,
    trendingMovieCardHeight = 200.dp,
    popularMovieCardHeight = 220.dp
)

val CompactStandardDimens = Dimens(
    extraSmall = 5.dp,
    small1 = 10.dp,
    small2 = 15.dp,
    small3 = 20.dp,
    medium1 = 30.dp,
    medium2 = 36.dp,
    medium3 = 40.dp,
    large = 80.dp,
    trendingMovieCardHeight = 220.dp,
    popularMovieCardHeight = 220.dp
)

// tablets
val MediumDimens = Dimens(
    extraSmall = 7.dp,
    small1 = 10.dp,
    small2 = 15.dp,
    small3 = 20.dp,
    medium1 = 30.dp,
    medium2 = 36.dp,
    medium3 = 40.dp,
    large = 110.dp,
    trendingMovieCardHeight = 260.dp,
    popularMovieCardHeight = 260.dp
)

val ExpandedDimens = Dimens(
    extraSmall = 10.dp,
    small1 = 15.dp,
    small2 = 20.dp,
    small3 = 25.dp,
    medium1 = 35.dp,
    medium2 = 30.dp,
    medium3 = 45.dp,
    large = 130.dp,
    trendingMovieCardHeight = 300.dp,
    popularMovieCardHeight = 300.dp
)
