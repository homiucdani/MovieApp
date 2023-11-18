package com.example.movieapp.presentation.details

import com.example.movieapp.domain.model.review.MovieReviewResult

sealed class DetailsEvent {

    data class OnTabSelected(val tabIndex: Int) : DetailsEvent()

    data class OnToggleReview(val movieReviewResult: MovieReviewResult) : DetailsEvent()

    object OnBackClick : DetailsEvent()
}