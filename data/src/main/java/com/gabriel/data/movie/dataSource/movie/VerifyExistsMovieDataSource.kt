package com.gabriel.data.movie.dataSource.movie

interface VerifyExistsMovieDataSource {
    suspend fun verifyExistsMovie(id: Int): Boolean
}
