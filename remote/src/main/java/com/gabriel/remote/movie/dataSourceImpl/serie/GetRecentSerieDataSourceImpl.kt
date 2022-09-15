package com.gabriel.remote.movie.dataSourceImpl.serie

import com.gabriel.data.movie.dataSource.serie.GetRecentSerieDataSource
import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.remote.movie.mapper.serie.SerieDetailResponseToDataMapper
import com.gabriel.remote.movie.modelsApi.serie.SerieDetailResponse
import com.gabriel.remote.movie.service.serie.SeriesService
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

class GetRecentSerieDataSourceImpl(
    private val service: SeriesService,
    private val mapper: SerieDetailResponseToDataMapper
) : GetRecentSerieDataSource {
    override suspend fun getRecentSerie(type: String): ResourceState<MovieData> {
        return try {
            val response = service.getRecentSerie()
            validateListResponse(response = response)
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    Timber
                        .tag("GetRecentFilmeDataSourceImpl/getRecentMovie")
                        .e("Error -> $t")
                    ResourceState.Undefined(message = "Erro de conexão.")
                }
                else -> {
                    Timber
                        .tag("GetRecentFilmeDataSourceImpl/getRecentMovie")
                        .e("Error -> $t")
                    ResourceState.Undefined(message = "Erro na conversão dos dados.")
                }
            }
        }
    }

    private fun validateListResponse(response: Response<SerieDetailResponse>):
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
