package com.gabriel.data.movie.dataStoreImpl

import com.gabriel.data.movie.dataSource.movie.SearchMovieDataSource
import com.gabriel.data.movie.dataStore.SearchMovieDataStore
import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState

class SearchMovieDataStoreImpl(
    private val source: SearchMovieDataSource
) : SearchMovieDataStore {
    override suspend fun searchMovie(query: String): ResourceState<List<MovieData>> {
        return source.searchMovie(query = query)
    }
}
