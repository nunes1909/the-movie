package com.gabriel.data.movie.dataStoreImpl

import com.gabriel.data.movie.dataSource.filme.GetSimilarFilmesDataSource
import com.gabriel.data.movie.dataSource.serie.GetSimilarSeriesDataSource
import com.gabriel.data.movie.dataStore.GetSimilarMoviesDataStore
import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.constants.ConstantsDomain.TYPE_FILME
import com.gabriel.domain.util.constants.ConstantsDomain.TYPE_SERIE
import com.gabriel.domain.util.state.ResourceState

class GetSimilarMoviesDataStoreImpl(
    private val filmesSource: GetSimilarFilmesDataSource,
    private val seriesSource: GetSimilarSeriesDataSource
) : GetSimilarMoviesDataStore {
    override suspend fun getSimilarMovies(type: String, movieId: Int): ResourceState<List<MovieData>> {
        return when (type) {
            TYPE_FILME -> { filmesSource.getSimilarFilmes(type = type, movieId = movieId) }
            TYPE_SERIE -> { seriesSource.getSimilarSeries(type = type, serieId = movieId) }
            else -> { ResourceState.Empty() }
        }
    }
}
