package com.example.movieapp.presentation.home

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.movieapp.presentation.home.components.HomeTopAppBar

@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            HomeTopAppBar(
                onSearchClick = {

                }
            )
        }
    ) { paddingValues ->

    }
}

