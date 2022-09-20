package com.gabriel.data.movie.dataSource.movie

import com.gabriel.domain.util.state.ResourceState

interface VerifyExistsMovieDataSource {
    suspend fun verifyExistsMovie(id: Int): ResourceState<Boolean>
}
