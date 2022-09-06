package com.gabriel.remote.features.genero.dataSource

import com.gabriel.data.features.filme.model.FilmeData
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.remote.features.filme.model.FilmeContainer
import com.gabriel.remote.features.genero.mapper.GeneroRemoteMapper
import com.gabriel.remote.features.genero.model.GeneroResponse
import com.gabriel.remote.features.genero.service.GeneroService
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

class GetGenerosDataSourceImpl(
    private val api: GeneroService,
    private val mapper: GeneroRemoteMapper
) : GetGenerosDataSource {
    override suspend fun getGenerosFilme(): List<GeneroResponse> {
        return try {
            val response = api.getGeneroFilme()
            validateListResponse(response = response)
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    Timber.tag("GetFilmesDataSourceImpl/getAllFilmes").e("Error -> $t")
                    listOf()
                }
                else -> {
                    Timber.tag("GetFilmesDataSourceImpl/getAllFilmes").e("Error -> $t")
                    listOf()
                }
            }
        }
    }

    override suspend fun getGenerosSerie(): List<GeneroResponse> {
        return try {
            val response = api.getGeneroFilme()
            validateListResponse(response = response)
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    Timber.tag("GetFilmesDataSourceImpl/getAllFilmes").e("Error -> $t")
                    listOf()
                }
                else -> {
                    Timber.tag("GetFilmesDataSourceImpl/getAllFilmes").e("Error -> $t")
                    listOf()
                }
            }
        }
    }

    private fun validateListResponse(response: Response<GeneroResponse>):
            List<GeneroResponse> {
        if (response.isSuccessful) {
        }
        return listOf()
    }
}

interface GetGenerosDataSource {
    suspend fun getGenerosFilme(): List<GeneroResponse>
    suspend fun getGenerosSerie(): List<GeneroResponse>
}
