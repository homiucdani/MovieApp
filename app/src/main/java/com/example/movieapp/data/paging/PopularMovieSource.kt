package com.example.movieapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.data.mapper.toMovieResult
import com.example.movieapp.data.remote.MovieApi
import com.example.movieapp.domain.model.MovieResult
import java.io.IOException

class PopularMovieSource(
    private val movieApi: MovieApi,
) : PagingSource<Int, MovieResult>() {

    override fun getRefreshKey(state: PagingState<Int, MovieResult>): Int? {
        return state.anchorPosition
    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResult> {
        val page = params.key ?: 1
        return try {
            val response = movieApi.getPopularMovies(page = page)
            LoadResult.Page(
                data = response.results.map { it.toMovieResult() },
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.results.isEmpty()) null else page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        }
    }
}