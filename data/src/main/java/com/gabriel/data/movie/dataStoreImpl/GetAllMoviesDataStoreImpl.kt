package com.gabriel.data.movie.dataStoreImpl

import com.gabriel.data.movie.dataSource.filme.GetAllFilmesDataSource
import com.gabriel.data.movie.dataSource.serie.GetAllSeriesDataSource
import com.gabriel.data.movie.dataStore.GetAllMoviesDataStore
import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.constants.ConstantsDomain.TYPE_FILME
import com.gabriel.domain.util.constants.ConstantsDomain.TYPE_SERIE
import com.gabriel.domain.util.state.ResourceState

class GetAllMoviesDataStoreImpl(
    private val filmesSource: GetAllFilmesDataSource,
    private val seriesSource: GetAllSeriesDataSource
) : GetAllMoviesDataStore {
    override suspend fun getAllMovies(type: String): ResourceState<List<MovieData>> {
        return when (type) {
            TYPE_FILME -> { filmesSource.getAllMovies(type = type) }
            TYPE_SERIE -> { seriesSource.getAllSeries(type = type) }
            else -> { ResourceState.Empty() }
        }
    }
}
