package com.gabriel.remote.features.serie.dataSource

import com.gabriel.data.features.serie.dataSource.GetSeriesDataSource
import com.gabriel.data.features.serie.model.SerieData
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.remote.features.serie.mapper.SerieDetailRemoteMapper
import com.gabriel.remote.features.serie.mapper.SerieRemoteMapper
import com.gabriel.remote.features.serie.model.SerieContainer
import com.gabriel.remote.features.serie.model.SerieDetailResponse
import com.gabriel.remote.features.serie.service.SeriesService
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

class GetSeriesDataSourceImpl(
    private val api: SeriesService,
    private val mapperSerie: SerieRemoteMapper,
    private val detailMapper: SerieDetailRemoteMapper
) : GetSeriesDataSource {
    override suspend fun getAllSeries(): ResourceState<List<SerieData>> {
        return try {
            val response = api.getAllSeries()
            validateListResponse(response = response)
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    Timber.tag("GetSeriesDataSourceImpl/getAllSeries").e("Error -> $t")
                    ResourceState.Undefined(message = "Erro de conexão.")
                }
                else -> {
                    Timber.tag("GetSeriesDataSourceImpl/getAllSeries").e("Error -> $t")
                    ResourceState.Undefined(message = "Erro na conversão dos dados.")
                }
            }
        }
    }

    private fun validateListResponse(response: Response<SerieContainer>):
            ResourceState<List<SerieData>> {
        if (response.isSuccessful) {
            response.body()?.let { values ->
                val resultsData = mapperSerie.mapFromDomainNonNull(values.results)
                return ResourceState.Undefined(data = resultsData)
            }
        }
        return ResourceState.Undefined(cod = response.code(), message = response.message())
    }

    override suspend fun getDetailSerie(serieId: Int): ResourceState<SerieData> {
        return try {
            val response = api.getDetailSerie(serieId = serieId)
            validateDetailResponse(response)
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    Timber.tag("GetSeriesDataSourceImpl/getDetailSerie").e("Error -> $t")
                    ResourceState.Undefined(message = "Erro de conexão.")
                }
                else -> {
                    Timber.tag("GetSeriesDataSourceImpl/getDetailSerie").e("Error -> $t")
                    ResourceState.Undefined(message = "Erro na conversão dos dados.")
                }
            }
        }
    }

    private fun validateDetailResponse(response: Response<SerieDetailResponse>):
            ResourceState<SerieData> {
        if (response.isSuccessful) {
            response.body()?.let { value ->
                val resultData = detailMapper.mapToData(value)
                return ResourceState.Undefined(data = resultData)
            }
        }
        return ResourceState.Undefined(cod = response.code(), message = response.message())
    }
}