package com.gabriel.data.movie.dataStoreImpl

import com.gabriel.data.movie.dataSource.movie.GetFavMovieDataSource
import com.gabriel.data.movie.dataStore.GetFavMovieDataStore
import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState

class GetFavMovieDataStoreImpl(private val dataSource: GetFavMovieDataSource) :
    GetFavMovieDataStore {
    override suspend fun getAllFav(query: String): ResourceState<List<MovieData>> {
        return dataSource.getAllFav(query = query)
    }
}
