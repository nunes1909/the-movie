package com.gabriel.remote.features.movie.datasource

import com.gabriel.data.features.movie.dataSource.GetAllFilmesDataSource
import com.gabriel.data.features.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.remote.features.movie.mapper.FilmeResponseToDataMapper
import com.gabriel.remote.features.movie.modelsApi.filme.FilmeContainer
import com.gabriel.remote.features.movie.service.FilmesService
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
            validateListResponse(response)
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    Timber.tag("GetFilmesDataSourceImpl/getAllFilmes").e("Error -> $t")
                    ResourceState.Undefined(message = "Erro de conexão.")
                }
                else -> {
                    Timber.tag("GetFilmesDataSourceImpl/getAllFilmes").e("Error -> $t")
                    ResourceState.Undefined(message = "Erro na conversão dos dados.")
                }
            }
        }
    }

    private fun validateListResponse(response: Response<FilmeContainer>):
            ResourceState<List<MovieData>> {
        if (response.isSuccessful) {
            response.body()?.let { values ->
                val resultsData = mapper.mapFromDomainNonNull(values.results)
                return ResourceState.Undefined(data = resultsData)
            }
        }
        return ResourceState.Undefined(cod = response.code(), message = response.message())
    }
}
