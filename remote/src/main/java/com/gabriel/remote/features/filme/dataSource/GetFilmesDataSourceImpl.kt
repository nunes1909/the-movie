package com.gabriel.remote.features.filme.dataSource

import com.gabriel.data.features.filme.dataSource.GetFilmesDataSource
import com.gabriel.data.features.filme.model.FilmeData
import com.gabriel.remote.features.filme.mapper.FilmeDetailRemoteMapper
import com.gabriel.remote.features.filme.mapper.FilmeRemoteMapper
import com.gabriel.remote.features.filme.model.FilmeContainer
import com.gabriel.remote.features.filme.model.FilmeDetailResponse
import com.gabriel.remote.features.filme.service.FilmesService
import com.gabriel.domain.util.state.ResourceState
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

class GetFilmesDataSourceImpl(
    private val api: FilmesService,
    private val mapperFilmes: FilmeRemoteMapper,
    private val mapperDetail: FilmeDetailRemoteMapper
) : GetFilmesDataSource {
    override suspend fun getAllFilmes(): ResourceState<List<FilmeData>> {
        return try {
            val response = api.getAllFilmes()
            validateListResponse(response = response)
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
            ResourceState<List<FilmeData>> {
        if (response.isSuccessful) {
            response.body()?.let { values ->
                val resultsData = mapperFilmes.mapFromDomainNonNull(values.results)
                return ResourceState.Undefined(data = resultsData)
            }
        }
        return ResourceState.Undefined(cod = response.code(), message = response.message())
    }

    override suspend fun getDetailFilme(filmeId: Int): ResourceState<FilmeData> {
        return try {
            val response = api.getDetailFilme(filmeId = filmeId)
            validateDetailResponse(response)
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    Timber.tag("GetFilmesDataSourceImpl/getDetailFilme").e("Error -> $t")
                    ResourceState.Undefined(message = "Erro de conexão.")
                }
                else -> {
                    Timber.tag("GetFilmesDataSourceImpl/getDetailFilme").e("Error -> $t")
                    ResourceState.Undefined(message = "Erro na conversão dos dados.")
                }
            }
        }
    }

    private fun validateDetailResponse(response: Response<FilmeDetailResponse>):
            ResourceState<FilmeData> {
        if (response.isSuccessful) {
            response.body()?.let { value ->
                val resultData = mapperDetail.mapToData(value)
                return ResourceState.Undefined(data = resultData)
            }
        }
        return ResourceState.Undefined(cod = response.code(), message = response.message())
    }
}
