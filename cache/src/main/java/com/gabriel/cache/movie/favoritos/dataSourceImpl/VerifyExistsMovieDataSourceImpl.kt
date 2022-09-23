package com.gabriel.cache.movie.favoritos.dataSourceImpl

import com.gabriel.cache.movie.favoritos.dao.FavoritosDao
import com.gabriel.data.movie.dataSource.movie.VerifyExistsMovieDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class VerifyExistsMovieDataSourceImpl(
    private val dao: FavoritosDao
) : VerifyExistsMovieDataSource {
    override suspend fun verifyExistsMovie(id: Int): Flow<Boolean> {
        return emitFlow(dao.verifyExistsMovie(id = id))
    }

    private fun emitFlow(ifExists: Flow<Boolean>) = flow {
        emit(ifExists.first())
    }
}
