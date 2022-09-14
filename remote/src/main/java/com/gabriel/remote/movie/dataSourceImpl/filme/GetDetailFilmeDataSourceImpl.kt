package com.gabriel.remote.movie.dataSourceImpl.filme

import com.gabriel.data.movie.dataSource.filme.GetDetailFilmeDataSource
import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.remote.movie.mapper.filme.FilmeDetailResponseToDataMapper
import com.gabriel.remote.movie.modelsApi.filme.FilmeDetailResponse
import com.gabriel.remote.movie.service.filme.FilmesService
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

class GetDetailFilmeDataSourceImpl(
    private val service: FilmesService,
    private val mapper: FilmeDetailResponseToDataMapper
) : GetDetailFilmeDataSource {
    override suspend fun getDetailFilme(type: String, movieId: Int): ResourceState<MovieData> {
        return try {
            val response = service.getDetailFilme(filmeId = movieId)
            validateListResponse(response = response)
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    Timber.tag("GetDetailFilmeDataSourceImpl/getDetailFilme").e("Error -> $t")
                    ResourceState.Undefined(message = "Erro de conexão.")
                }
                else -> {
                    Timber.tag("GetDetailFilmeDataSourceImpl/getDetailFilme").e("Error -> $t")
                    ResourceState.Undefined(message = "Erro na conversão dos dados.")
                }
            }
        }
    }

    private fun validateListResponse(response: Response<FilmeDetailResponse>):
            ResourceState<MovieData> {
        if (response.isSuccessful) {
            response.body()?.let { value ->
                val resultsData = mapper.mapToData(type = value)
                return ResourceState.Undefined(data = resultsData)
            }
        }
        return ResourceState.Undefined(cod = response.code(), message = response.message())
    }
}
