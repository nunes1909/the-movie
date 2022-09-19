package com.gabriel.data.movie.dataSource

import com.gabriel.domain.util.state.ResourceState

interface VerifyExistsMovieDataSource {
    suspend fun verifyExistsMovie(id: Int): ResourceState<Boolean>
}
