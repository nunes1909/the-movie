package com.gabriel.data.features.movie.dataStoreImpl

import com.gabriel.data.features.movie.dataSource.filme.GetDetailFilmeDataSource
import com.gabriel.data.features.movie.dataSource.serie.GetDetailSerieDataSource
import com.gabriel.data.features.movie.dataStore.GetDetailMovieDataStore
import com.gabriel.data.features.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState

class GetDetailMovieDataStoreImpl(
    private val filmesSource: GetDetailFilmeDataSource,
    private val seriesService: GetDetailSerieDataSource
) : GetDetailMovieDataStore {
    override suspend fun getDetailMovie(movieId: Int, type: String): ResourceState<MovieData> {
        return when (type) {
            "movie" -> { filmesSource.getDetailFilme(type = type, movieId = movieId) }
            "tv" -> { seriesService.getDetailSerie(type = type, movieId = movieId) }
            else -> { ResourceState.Empty() }
        }
    }
}
