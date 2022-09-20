package com.gabriel.remote.movie.dataSourceImpl.filme

import com.gabriel.data.movie.dataSource.filme.GetDetailFilmeDataSource
import com.gabriel.data.movie.model.MovieData
import com.gabriel.data.video.model.VideoData
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.remote.movie.mapper.filme.FilmeDetailResponseToDataMapper
import com.gabriel.remote.movie.modelsApi.filme.FilmeDetailResponse
import com.gabriel.remote.video.model.filme.FilmeVideoContainer
import com.gabriel.remote.movie.service.filme.FilmesService
import com.gabriel.remote.movie.service.video.VideoService
import com.gabriel.remote.video.mapper.filme.VideoFilmeRemoteMapper
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

class GetDetailFilmeDataSourceImpl(
    private val filmeService: FilmesService,
    private val videoService: VideoService,
    private val mapper: FilmeDetailResponseToDataMapper
) : GetDetailFilmeDataSource {
    override suspend fun getDetailFilme(type: String, movieId: Int): ResourceState<MovieData> {
        return try {
            val detailResponse = filmeService.getDetailFilme(filmeId = movieId)
            val videoResponse = videoService.getFilmeVideo(filmeId = movieId)

            val videosData = validateVideoResponse(response = videoResponse)
            validateListResponseAndReturn(response = detailResponse, videos = videosData)
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
        videos: List<VideoData>
    ): ResourceState<MovieData> {
        if (response.isSuccessful) {
            response.body()?.let { value ->
                val resultData = mapper.mapToData(type = value).apply { this.videos = videos }
                return ResourceState.Undefined(data = resultData)
            }
        }
        return ResourceState.Undefined(cod = response.code(), message = response.message())
    }
}
