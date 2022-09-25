package com.gabriel.remote.movie.dataSourceImpl.serie

import com.gabriel.data.movie.dataSource.serie.GetSimilarSeriesDataSource
import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.remote.movie.mapper.serie.SerieResponseToDataMapper
import com.gabriel.remote.movie.modelsApi.serie.SerieContainer
import com.gabriel.remote.movie.service.serie.SeriesService
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

class GetSimilarSeriesDataSourceImpl(
    private val service: SeriesService,
    private val mapper: SerieResponseToDataMapper
) : GetSimilarSeriesDataSource {
    override suspend fun getSimilarSeries(
        type: String,
        serieId: Int
    ): ResourceState<List<MovieData>> {
        return try {
            val response = service.getSimilarSeries(serieId = serieId)
            validateListResponse(response = response)
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    Timber
                        .tag("GetAllFilmesDataSourceImpl/getAllMovies")
                        .e("Error -> $t")
                    ResourceState.Error(message = "Erro de conexão.")
                }
                else -> {
                    Timber
                        .tag("GetAllFilmesDataSourceImpl/getAllMovies")
                        .e("Error -> $t")
                    ResourceState.Error(message = "Erro na conversão dos dados.")
                }
            }
        }
    }

    private fun validateListResponse(response: Response<SerieContainer>):
            ResourceState<List<MovieData>> {
        if (response.isSuccessful) {
            response.body()?.let { values ->
                val resultsData = mapper.mapToDataNonNull(entity = values.results)
                return ResourceState.Success(data = resultsData)
            }
        }
        return ResourceState.Error(cod = response.code(), message = response.message())
    }
}
