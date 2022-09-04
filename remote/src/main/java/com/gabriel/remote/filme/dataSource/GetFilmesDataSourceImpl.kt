package com.gabriel.remote.filme.dataSource

import com.gabriel.data.filme.dataSource.GetFilmesDataSource
import com.gabriel.data.filme.model.FilmeData
import com.gabriel.data.util.state.ResourceState
import com.gabriel.remote.filme.mapper.FilmeDetailRemoteMapper
import com.gabriel.remote.filme.mapper.FilmeRemoteMapper
import com.gabriel.remote.filme.model.FilmeContainer
import com.gabriel.remote.filme.model.FilmeDetailResponse
import com.gabriel.remote.filme.service.FilmesService
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
            responseListValidate(response)
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    Timber.tag("GetFilmesDataSourceImpl").e("Error -> $t")
                    ResourceState.Error(message = "Erro de conexão.")
                }
                else -> {
                    Timber.tag("GetFilmesDataSourceImpl").e("Error -> $t")
                    ResourceState.Error(message = "Erro na conversão dos dados.")
                }
            }
        }
    }

    private fun responseListValidate(response: Response<FilmeContainer>): ResourceState<List<FilmeData>> {
        if (response.isSuccessful) {
            response.body()?.let { values ->
                val resultsData = mapperFilmes.mapFromDataNonNull(values.results)
                return ResourceState.Succes(resultsData)
            }
        }
        return ResourceState.Error(cod = response.code(), message = response.message())
    }

    override suspend fun searchFilmes(query: String): ResourceState<List<FilmeData>> {
        return try {
            val response = api.searchFilmes(query = query)
            responseListValidate(response)
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    Timber.tag("GetFilmesDataSourceImpl").e("Error -> $t")
                    ResourceState.Error(message = "Erro de conexão.")
                }
                else -> {
                    Timber.tag("GetFilmesDataSourceImpl").e("Error -> $t")
                    ResourceState.Error(message = "Erro na conversão dos dados.")
                }
            }
        }
    }

    override suspend fun getDetailFilme(id: Int): ResourceState<FilmeData> {
        return try {
            val response = api.getDetailFilme(filmeId = id)
            responseValidate(response)
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    Timber.tag("GetFilmesDataSourceImpl").e("Error -> $t")
                    ResourceState.Error(message = "Erro de conexão.")
                }
                else -> {
                    Timber.tag("GetFilmesDataSourceImpl").e("Error -> $t")
                    ResourceState.Error(message = "Erro na conversão dos dados.")
                }
            }
        }
    }

    private fun responseValidate(response: Response<FilmeDetailResponse>): ResourceState<FilmeData> {
        if (response.isSuccessful) {
            response.body()?.let { value ->
                val resultsData = mapperDetail.mapToData(value)
                return ResourceState.Succes(resultsData)
            }
        }
        return ResourceState.Error(cod = response.code(), message = response.message())
    }
}