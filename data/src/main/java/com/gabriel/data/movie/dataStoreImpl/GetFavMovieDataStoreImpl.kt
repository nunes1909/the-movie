package com.gabriel.data.movie.dataStoreImpl

import com.gabriel.data.movie.dataSource.movie.GetFavMovieCacheDataSource
import com.gabriel.data.movie.dataStore.GetFavMovieDataStore
import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState
import kotlinx.coroutines.flow.Flow

class GetFavMovieDataStoreImpl(private val source: GetFavMovieCacheDataSource): GetFavMovieDataStore {
    override suspend fun getAllFav(query: String): Flow<ResourceState<List<MovieData>>> {
        return source.getAllFav(query = query)
    }
}
