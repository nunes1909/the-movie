package com.gabriel.remote.features.filme.dataSource

import com.gabriel.data.features.filme.dataSource.GetFilmesSimilaresDataSource
import com.gabriel.data.features.filme.model.FilmeData
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.remote.features.filme.mapper.FilmeRemoteMapper
import com.gabriel.remote.features.filme.model.FilmeContainer
import com.gabriel.remote.features.filme.service.FilmesService
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

class GetFilmesSimilaresDataSourceImpl(
    private val api: FilmesService,
    private val mapperFilmes: FilmeRemoteMapper
) : GetFilmesSimilaresDataSource {
    override suspend fun getFilmesSimilares(filmeId: Int): ResourceState<List<FilmeData>> {
        return try {
            val response = api.getSimilarMovies(filmeId = filmeId)
            validateListResponse(response = response)
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    Timber.tag("GetFilmesSimilaresDataSource/getFilmesSimilares").e("Error -> $t")
                    ResourceState.Undefined(message = "Erro de conexão.")
                }
                else -> {
                    Timber.tag("GetFilmesSimilaresDataSource/getFilmesSimilares").e("Error -> $t")
                    ResourceState.Undefined(message = "Erro na conversão dos dados.")
                }
            }
        }
    }

    private fun validateListResponse(response: Response<FilmeContainer>):
            ResourceState<List<FilmeData>> {
        if (response.isSuccessful) {
            response.body()?.let { values ->
                val resultsData = mapperFilmes.mapFromDomainNonNull(values.results)
                return ResourceState.Undefined(data = resultsData)
            }
        }
        return ResourceState.Undefined(cod = response.code(), message = response.message())
    }
}
