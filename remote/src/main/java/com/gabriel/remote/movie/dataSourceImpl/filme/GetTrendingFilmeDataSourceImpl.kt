package com.gabriel.remote.movie.dataSourceImpl.filme

import com.gabriel.data.movie.dataSource.filme.GetTrendingFilmeDataSource
import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.remote.movie.mapper.filme.FilmeResponseToDataMapper
import com.gabriel.remote.movie.modelsApi.filme.FilmeContainer
import com.gabriel.remote.movie.service.trending.TrendingService
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

class GetTrendingFilmeDataSourceImpl(
    private val service: TrendingService,
    private val mapper: FilmeResponseToDataMapper
) : GetTrendingFilmeDataSource {
    override suspend fun getTrendingFilme(type: String): ResourceState<List<MovieData>> {
        return try {
            val response = service.getTrendingFilme(typeMovie = type)
            validateListResponse(response = response)
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    Timber
                        .tag("GetRecentFilmeDataSourceImpl/getRecentMovie")
                        .e("Error -> $t")
                    ResourceState.Error(message = "Erro de conexão.")
                }
                else -> {
                    Timber
                        .tag("GetRecentFilmeDataSourceImpl/getRecentMovie")
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
                val resultData = mapper.mapToDataNonNull(values.results)
                return ResourceState.Success(data = resultData)
            }
        }
        return ResourceState.Error(cod = response.code(), message = response.message())
    }
}
