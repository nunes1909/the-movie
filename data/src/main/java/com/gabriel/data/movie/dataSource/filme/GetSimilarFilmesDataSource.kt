package com.gabriel.data.movie.dataSource.filme

import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState

interface GetSimilarFilmesDataSource {
    suspend fun getSimilarFilmes(type: String, movieId: Int): ResourceState<List<MovieData>>
}
