package com.example.movieapp.navigation

sealed class Screen(val route: String) {

    object Home : Screen(route = "home_screen")
    object Details : Screen(route = "details_screen/{movie_id}") {
        fun passMovieId(movieId: Int) = "details_screen/$movieId"
    }
    object Search : Screen(route = "search_screen")
}
