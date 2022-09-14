package com.gabriel.remote.movie.dataSourceImpl.filme

import com.gabriel.data.movie.dataSource.filme.GetRecentFilmeDataSource
import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.remote.movie.mapper.filme.FilmeDetailResponseToDataMapper
import com.gabriel.remote.movie.mapper.filme.FilmeResponseToDataMapper
import com.gabriel.remote.movie.modelsApi.filme.FilmeContainer
import com.gabriel.remote.movie.modelsApi.filme.FilmeDetailResponse
import com.gabriel.remote.movie.service.filme.FilmesService
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

class GetRecentFilmeDataSourceImpl(
    private val service: FilmesService,
    private val mapper: FilmeDetailResponseToDataMapper
) : GetRecentFilmeDataSource {
    override suspend fun getRecentFilme(type: String): ResourceState<MovieData> {
        return try {
            val response = service.getRecentFilme()
            validateListResponse(response = response)
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    Timber.tag("GetRecentFilmeDataSourceImpl/getRecentMovie").e("Error -> $t")
                    ResourceState.Undefined(message = "Erro de conexão.")
                }
                else -> {
                    Timber.tag("GetRecentFilmeDataSourceImpl/getRecentMovie").e("Error -> $t")
                    ResourceState.Undefined(message = "Erro na conversão dos dados.")
                }
            }
        }
    }

    private fun validateListResponse(response: Response<FilmeDetailResponse>):
            ResourceState<MovieData> {
        if (response.isSuccessful) {
            response.body()?.let { value ->
                val resultData = mapper.mapToData(value)
                return ResourceState.Undefined(data = resultData)
            }
        }
        return ResourceState.Undefined(cod = response.code(), message = response.message())
    }
}
