package com.gabriel.data.movie.dataStoreImpl

import com.gabriel.data.movie.dataSource.filme.GetDetailFilmeDataSource
import com.gabriel.data.movie.dataSource.serie.GetDetailSerieDataSource
import com.gabriel.data.movie.dataStore.GetDetailMovieDataStore
import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState

class GetDetailMovieDataStoreImpl(
    private val filmesSource: GetDetailFilmeDataSource,
    private val seriesService: GetDetailSerieDataSource
) : GetDetailMovieDataStore {
    override suspend fun getDetailMovie(type: String, movieId: Int): ResourceState<MovieData> {
        return when (type) {
            "movie" -> { filmesSource.getDetailFilme(type = type, movieId = movieId) }
            "tv" -> { seriesService.getDetailSerie(type = type, movieId = movieId) }
            else -> { ResourceState.Empty() }
        }
    }
}
