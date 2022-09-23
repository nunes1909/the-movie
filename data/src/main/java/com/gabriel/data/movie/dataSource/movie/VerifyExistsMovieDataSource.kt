package com.gabriel.data.movie.dataSource.movie

import kotlinx.coroutines.flow.Flow

interface VerifyExistsMovieDataSource {
    suspend fun verifyExistsMovie(id: Int): Flow<Boolean>
}
