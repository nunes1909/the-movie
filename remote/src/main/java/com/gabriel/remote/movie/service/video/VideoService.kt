package com.gabriel.remote.movie.service.video

import com.gabriel.remote.movie.modelsApi.filme.FilmeVideoContainer
import com.gabriel.remote.movie.modelsApi.serie.SerieVideoContainer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface VideoService {
    @GET("movie/{movie_id}/videos")
    suspend fun getFilmeVideo(
        @Path(value = "movie_id")
        filmeId: Int
    ): Response<FilmeVideoContainer>

    @GET("tv/{tv_id}/videos")
    suspend fun getSerieVideo(
        @Path(value = "tv_id")
        serieId: Int
    ): Response<SerieVideoContainer>
}
