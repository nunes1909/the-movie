package com.gabriel.data.features.movie.dataStoreImpl

import com.gabriel.data.features.movie.dataSource.filme.GetAllFilmesDataSource
import com.gabriel.data.features.movie.dataSource.serie.GetAllSeriesDataSource
import com.gabriel.data.features.movie.dataStore.GetAllMoviesDataStore
import com.gabriel.data.features.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState

class GetAllMoviesDataStoreImpl(
    private val filmesSource: GetAllFilmesDataSource,
    private val seriesSource: GetAllSeriesDataSource
) : GetAllMoviesDataStore {
    override suspend fun getAllMovies(type: String): ResourceState<List<MovieData>> {
        return when (type) {
            "movie" -> { filmesSource.getAllMovies(type = type) }
            "tv" -> { seriesSource.getAllSeries(type = type) }
            else -> { ResourceState.Empty() }
        }
    }
}