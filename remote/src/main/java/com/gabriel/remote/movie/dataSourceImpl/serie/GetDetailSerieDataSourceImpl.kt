package com.gabriel.remote.movie.dataSourceImpl.serie

import com.gabriel.data.movie.dataSource.serie.GetDetailSerieDataSource
import com.gabriel.data.movie.model.MovieData
import com.gabriel.data.video.model.VideoData
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.remote.movie.mapper.serie.SerieDetailResponseToDataMapper
import com.gabriel.remote.movie.modelsApi.serie.SerieDetailResponse
import com.gabriel.remote.movie.service.serie.SeriesService
import com.gabriel.remote.movie.service.traducao.TraducaoService
import com.gabriel.remote.movie.service.video.VideoService
import com.gabriel.remote.traducao.model.filme.TraducaoFilmeContainer
import com.gabriel.remote.traducao.model.filme.TraducaoFilmeResponse
import com.gabriel.remote.util.constants.ConstantsRemote
import com.gabriel.remote.video.mapper.serie.VideoSerieRemoteMapper
import com.gabriel.remote.video.model.serie.SerieVideoContainer
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

class GetDetailSerieDataSourceImpl(
    private val serieService: SeriesService,
    private val videoService: VideoService,
    private val traducaoService: TraducaoService,
    private val mapper: SerieDetailResponseToDataMapper
) : GetDetailSerieDataSource {
    override suspend fun getDetailSerie(type: String, movieId: Int): ResourceState<MovieData> {
        return try {
            val detailResponse = serieService.getDetailSerie(serieId = movieId)
            val videoResponse = videoService.getSerieVideo(serieId = movieId)
            val traducaoResponse = traducaoService.getTraducaoSerie(serieId = movieId)

            val traducoes = validateTraducaoResponse(response = traducaoResponse)
            val videosData = validateVideoResponse(response = videoResponse)

            validateListResponse(
                response = detailResponse,
                videos = videosData,
                traducoes = traducoes
            )
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    Timber
                        .tag("GetDetailSerieDataSourceImpl/getDetailSerie")
                        .e("Error -> $t")
                    ResourceState.Error(message = "Erro de conexão.")
                }
                else -> {
                    Timber
                        .tag("GetDetailSerieDataSourceImpl/getDetailSerie")
                        .e("Error -> $t")
                    ResourceState.Error(message = "Erro na conversão dos dados.")
                }
            }
        }
    }

    private fun validateTraducaoResponse(response: Response<TraducaoFilmeContainer>):
            List<TraducaoFilmeResponse> {
        if (response.isSuccessful) {
            response.body()?.let { value ->
                return value.translations
            }
        }
        return listOf()
    }

    private fun validateVideoResponse(response: Response<SerieVideoContainer>):
            List<VideoData> {
        val mapper = VideoSerieRemoteMapper()
        if (response.isSuccessful) {
            response.body()?.let { value ->
                return mapper.mapToDataNonNull(value.results)
            }
        }
        return listOf()
    }

    private fun validateListResponse(
        response: Response<SerieDetailResponse>,
        videos: List<VideoData>,
        traducoes: List<TraducaoFilmeResponse>
    ): ResourceState<MovieData> {
        val pt = traducoes.first { it.pais == ConstantsRemote.TYPE_BR }
        if (response.isSuccessful) {
            response.body()?.let { value ->
                val resultsData = mapper.mapToData(type = value).apply {
                    this.videos = videos
                    this.description = pt.filme.descricao
                }
                return ResourceState.Success(data = resultsData)
            }
        }
        return ResourceState.Error(cod = response.code(), message = response.message())
    }
}
