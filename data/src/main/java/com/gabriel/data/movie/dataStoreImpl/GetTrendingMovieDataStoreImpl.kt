package com.gabriel.data.movie.dataStoreImpl

import com.gabriel.data.movie.dataSource.filme.GetTrendingFilmeDataSource
import com.gabriel.data.movie.dataSource.serie.GetTrendingSerieDataSource
import com.gabriel.data.movie.dataStore.GetTrendingMovieDataStore
import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.constants.ConstantsDomain.TYPE_FILME
import com.gabriel.domain.util.constants.ConstantsDomain.TYPE_SERIE
import com.gabriel.domain.util.state.ResourceState

class GetTrendingMovieDataStoreImpl(
    private val filmesSource: GetTrendingFilmeDataSource,
    private val seriesSource: GetTrendingSerieDataSource
) : GetTrendingMovieDataStore {
    override suspend fun getTrendingMovie(type: String): ResourceState<List<MovieData>> {
        return when (type) {
            TYPE_FILME -> {
                filmesSource.getTrendingFilme(type = type)
            }
            TYPE_SERIE -> {
                seriesSource.getTrendingSerie(type = type)
            }
            else -> {
                ResourceState.Empty()
            }
        }
    }
}
