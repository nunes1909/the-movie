package com.gabriel.cache.movie.favoritos.dataSourceImpl

import com.gabriel.cache.movie.favoritos.dao.FavoritosDao
import com.gabriel.cache.movie.favoritos.mapper.MovieCacheMapper
import com.gabriel.data.movie.dataSource.VerifyExistsMovieDataSource
import com.gabriel.domain.util.state.ResourceState

class VerifyExistsMovieDataSourceImpl(
    private val dao: FavoritosDao
) : VerifyExistsMovieDataSource {
    override suspend fun verifyExistsMovie(idApi: Int): ResourceState<Boolean> {
        val ifExists = dao.verifyExistsMovie(idApi = idApi)
        return ResourceState.Undefined(data = ifExists)
    }
}
