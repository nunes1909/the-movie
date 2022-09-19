package com.gabriel.data.movie.dataSource

import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState

interface VerifyExistsMovieDataSource {
    suspend fun verifyExistsMovie(idApi: Int): ResourceState<Boolean>
}
