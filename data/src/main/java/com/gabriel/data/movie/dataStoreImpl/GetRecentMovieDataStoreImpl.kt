package com.gabriel.data.movie.dataStoreImpl

import com.gabriel.data.movie.dataSource.filme.GetRecentFilmeDataSource
import com.gabriel.data.movie.dataSource.serie.GetDetailSerieDataSource
import com.gabriel.data.movie.dataStore.GetRecentMovieDataStore
import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState

class GetRecentMovieDataStoreImpl(
    private val filmesSource: GetRecentFilmeDataSource,
    private val seriesService: GetDetailSerieDataSource
) : GetRecentMovieDataStore {
    override suspend fun getRecentMovie(type: String): ResourceState<MovieData> {
        return when (type) {
            "movie" -> { filmesSource.getRecentFilme(type = type) }
            "tv" -> { filmesSource.getRecentFilme(type = type) }
            else -> { ResourceState.Empty() }
        }
    }
}
