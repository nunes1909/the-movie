package com.gabriel.remote.movie.dataSourceImpl.filme

import com.gabriel.data.movie.dataSource.filme.GetDetailFilmeDataSource
import com.gabriel.data.movie.model.MovieData
import com.gabriel.data.video.model.VideoData
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.remote.movie.mapper.filme.FilmeDetailResponseToDataMapper
import com.gabriel.remote.movie.modelsApi.filme.FilmeDetailResponse
import com.gabriel.remote.movie.service.filme.FilmesService
import com.gabriel.remote.movie.service.traducao.TraducaoService
import com.gabriel.remote.movie.service.video.VideoService
import com.gabriel.remote.traducao.model.filme.TraducaoFilmeContainer
import com.gabriel.remote.traducao.model.filme.TraducaoFilmeResponse
import com.gabriel.remote.util.constants.ConstantsRemote.TYPE_BR
import com.gabriel.remote.video.mapper.filme.VideoFilmeRemoteMapper
import com.gabriel.remote.video.model.filme.FilmeVideoContainer
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

class GetDetailFilmeDataSourceImpl(
    private val filmeService: FilmesService,
    private val videoService: VideoService,
    private val traducaoService: TraducaoService,
    private val filmeMapper: FilmeDetailResponseToDataMapper
) : GetDetailFilmeDataSource {
    override suspend fun getDetailFilme(type: String, movieId: Int): ResourceState<MovieData> {
        return try {
            val detailResponse = filmeService.getDetailFilme(filmeId = movieId)
            val videoResponse = videoService.getFilmeVideo(filmeId = movieId)
            val traducaoResponse = traducaoService.getTraducaoFilme(filmeId = movieId)

            val traducoes = validateTraducaoResponse(response = traducaoResponse)
            val videosData = validateVideoResponse(response = videoResponse)

            validateListResponseAndReturn(
                response = detailResponse,
                videos = videosData,
                traducoes = traducoes
            )
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    Timber
                        .tag("GetDetailFilmeDataSourceImpl/getDetailFilme")
                        .e("Error -> $t")
                    ResourceState.Undefined(message = "Erro de conexão.")
                }
                else -> {
                    Timber
                        .tag("GetDetailFilmeDataSourceImpl/getDetailFilme")
                        .e("Error -> $t")
                    ResourceState.Undefined(message = "Erro na conversão dos dados.")
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

    private fun validateVideoResponse(response: Response<FilmeVideoContainer>):
            List<VideoData> {
        val mapper = VideoFilmeRemoteMapper()
        if (response.isSuccessful) {
            response.body()?.let { value ->
                return mapper.mapToDataNonNull(value.results)
            }
        }
        return listOf()
    }

    private fun validateListResponseAndReturn(
        response: Response<FilmeDetailResponse>,
        videos: List<VideoData>,
        traducoes: List<TraducaoFilmeResponse>
    ): ResourceState<MovieData> {
        val pt = traducoes.first { it.pais == TYPE_BR }
        if (response.isSuccessful) {
            response.body()?.let { value ->
                val resultData = filmeMapper.mapToData(type = value).apply {
                    this.videos = videos
                    this.description = pt.filme.descricao
                }
                return ResourceState.Undefined(data = resultData)
            }
        }
        return ResourceState.Undefined(cod = response.code(), message = response.message())
    }
}
