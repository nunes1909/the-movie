package com.gabriel.data.features.movie.dataStoreImpl

import com.gabriel.data.features.movie.dataSource.movie.SearchMovieDataSource
import com.gabriel.data.features.movie.dataStore.SearchMovieDataStore
import com.gabriel.data.features.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState

class SearchMovieDataStoreImpl(
    private val source: SearchMovieDataSource
) : SearchMovieDataStore {
    override suspend fun searchMovie(query: String): ResourceState<List<MovieData>> {

    }
}