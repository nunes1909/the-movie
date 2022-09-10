package com.gabriel.remote.features.serie.dataSource

import com.gabriel.data.features.serie.dataSource.GetTrandingSeriesDataSource
import com.gabriel.data.features.serie.model.SerieData
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.remote.features.serie.mapper.SerieRemoteMapper
import com.gabriel.remote.features.serie.model.SerieContainer
import com.gabriel.remote.features.serie.service.SeriesService
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

class GetTrandingSeriesDataSourceImpl(
    private val api: SeriesService,
    private val mapper: SerieRemoteMapper
) : GetTrandingSeriesDataSource {
    override suspend fun getTrending(
        mediaType: String,
        timeWindow: String
    ): ResourceState<List<SerieData>> {
        return try {
            val response = api.getTrending(mediaType = mediaType, timeWindow = timeWindow)
            validaListResponse(response)
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    Timber.tag("GetTrandingSeriesDataSourceImpl/getTrending").e("Error -> $t")
                    ResourceState.Undefined(message = "Erro de conexão.")
                }
                else -> {
                    Timber.tag("GetTrandingSeriesDataSourceImpl/getTrending").e("Error -> $t")
                    ResourceState.Undefined(message = "Erro na conversão dos dados.")
                }
            }
        }
    }

    private fun validaListResponse(response: Response<SerieContainer>): ResourceState<List<SerieData>> {
        if (response.isSuccessful) {
            response.body()?.let { values ->
                val resultsData = mapper.mapFromDomainNonNull(values.results)
                return ResourceState.Undefined(data = resultsData)
            }
        }
        return ResourceState.Undefined(cod = response.code(), message = response.message())
    }
}
