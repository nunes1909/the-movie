package com.gabriel.remote.movie.dataSourceImpl.filme

import com.gabriel.data.movie.dataSource.filme.GetAllFilmesDataSource
import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.remote.movie.mapper.filme.FilmeResponseToDataMapper
import com.gabriel.remote.movie.modelsApi.filme.FilmeContainer
import com.gabriel.remote.movie.service.filme.FilmesService
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

class GetAllFilmesDataSourceImpl(
    private val service: FilmesService,
    private val mapper: FilmeResponseToDataMapper
) : GetAllFilmesDataSource {
    override suspend fun getAllMovies(type: String): ResourceState<List<MovieData>> {
        return try {
            val response = service.getAllFilmes()
            validateListResponse(response = response)
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    Timber
                        .tag("GetAllFilmesDataSourceImpl/getAllMovies")
                        .e("Error -> $t")
                    ResourceState.Error(message = "Erro de conexão.")
                }
                else -> {
                    Timber
                        .tag("GetAllFilmesDataSourceImpl/getAllMovies")
                        .e("Error -> $t")
                    ResourceState.Error(message = "Erro na conversão dos dados.")
                }
            }
        }
    }

    private fun validateListResponse(response: Response<FilmeContainer>):
            ResourceState<List<MovieData>> {
        if (response.isSuccessful) {
            response.body()?.let { values ->
                val resultsData = mapper.mapToDataNonNull(remote = values.results)
                return ResourceState.Success(data = resultsData)
            }
        }
        return ResourceState.Error(cod = response.code(), message = response.message())
    }
}
