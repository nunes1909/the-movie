package com.gabriel.remote.features.movie.dataSourceImpl.serie

import com.gabriel.data.features.movie.dataSource.serie.GetAllSeriesDataSource
import com.gabriel.data.features.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.remote.features.movie.mapper.serie.SerieResponseToDataMapper
import com.gabriel.remote.features.serie.model.SerieContainer
import com.gabriel.remote.features.serie.service.SeriesService
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

class GetAllSeriesDataSourceImpl(
    private val service: SeriesService,
    private val mapper: SerieResponseToDataMapper
) : GetAllSeriesDataSource {
    override suspend fun getAllSeries(type: String): ResourceState<List<MovieData>> {
        return try {
            val response = service.getAllSeries()
            validateListResponse(response)
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

    private fun validateListResponse(response: Response<SerieContainer>):
            ResourceState<List<MovieData>> {
        if (response.isSuccessful) {
            response.body()?.let { values ->
                val resultsData = mapper.mapFromDomainNonNull(values.results)
                return ResourceState.Undefined(data = resultsData)
            }
        }
        return ResourceState.Undefined(cod = response.code(), message = response.message())
    }
}
