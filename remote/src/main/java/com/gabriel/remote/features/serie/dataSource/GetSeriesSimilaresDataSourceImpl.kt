package com.gabriel.remote.features.serie.dataSource

import com.gabriel.data.features.serie.dataSource.GetSeriesSimilaresDataSource
import com.gabriel.data.features.serie.model.SerieData
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.remote.features.serie.mapper.SerieRemoteMapper
import com.gabriel.remote.features.serie.model.SerieContainer
import com.gabriel.remote.features.serie.service.SeriesService
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

class GetSeriesSimilaresDataSourceImpl(
    private val api: SeriesService,
    private val mapperSeries: SerieRemoteMapper
) : GetSeriesSimilaresDataSource {
    override suspend fun getSeriesSimilares(serieId: Int): ResourceState<List<SerieData>> {
        return try {
            val response = api.getSimilarSeries(serieId = serieId)
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

    private fun validateListResponse(response: Response<SerieContainer>):
            ResourceState<List<SerieData>> {
        if (response.isSuccessful) {
            response.body()?.let { values ->
                val resultsData = mapperSeries.mapFromDomainNonNull(values.results)
                return ResourceState.Undefined(data = resultsData)
            }
        }
        return ResourceState.Undefined(cod = response.code(), message = response.message())
    }
}
