package com.example.movieapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.lifecycle.lifecycleScope
import com.example.movieapp.data.remote.MovieApi
import com.example.movieapp.ui.theme.MovieAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var movieApi: MovieApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
                Column {
                    Button(onClick = {
                        lifecycleScope.launch(Dispatchers.IO) {
//                            val result = movieApi.getPopularMovies()
//                            val result2 = movieApi.getTrendingMovies()
//                            Log.d("RESULT", "onCreate: $result")
//                            Log.d("RESULT", "onCreate: $result2")

                            val movie = movieApi.getMovieById(movieId = "299054")
                            Log.d("MOVIE", "onCreate: $movie")
                        }
                    }) {
                        Text(text = "Get movies")
                    }
                }
            }
        }
    }
}
