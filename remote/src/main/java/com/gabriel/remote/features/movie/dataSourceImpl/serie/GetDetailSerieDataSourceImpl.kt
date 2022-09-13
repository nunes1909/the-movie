package com.gabriel.remote.features.movie.dataSourceImpl.serie

import com.gabriel.data.features.movie.dataSource.serie.GetDetailSerieDataSource
import com.gabriel.data.features.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.remote.features.movie.mapper.serie.SerieDetailResponseToDataMapper
import com.gabriel.remote.features.movie.modelsApi.serie.SerieDetailResponse
import com.gabriel.remote.features.movie.service.serie.SeriesService
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

class GetDetailSerieDataSourceImpl(
    private val service: SeriesService,
    private val mapper: SerieDetailResponseToDataMapper
) : GetDetailSerieDataSource {
    override suspend fun getDetailSerie(movieId: Int, type: String): ResourceState<MovieData> {
        return try {
            val response = service.getDetailSerie(serieId = movieId)
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

    private fun validateListResponse(response: Response<SerieDetailResponse>):
            ResourceState<MovieData> {
        if (response.isSuccessful) {
            response.body()?.let { value ->
                val resultsData = mapper.mapToData(value)
                return ResourceState.Undefined(data = resultsData)
            }
        }
        return ResourceState.Undefined(cod = response.code(), message = response.message())
    }
}
