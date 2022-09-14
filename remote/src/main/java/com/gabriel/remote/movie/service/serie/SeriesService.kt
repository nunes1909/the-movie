package com.gabriel.remote.movie.service.serie

import com.gabriel.remote.movie.modelsApi.serie.SerieContainer
import com.gabriel.remote.movie.modelsApi.serie.SerieDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SeriesService {

    @GET("tv/popular")
    suspend fun getAllSeries(): Response<SerieContainer>

    @GET("tv/{tv_id}")
    suspend fun getDetailSerie(
        @Path(value = "tv_id", encoded = true)
        serieId: Int
    ): Response<SerieDetailResponse>

    @GET("trending/{media_type}/{time_window}")
    suspend fun getTrending(
        @Path(value = "media_type", encoded = true)
        mediaType: String,
        @Path(value = "time_window", encoded = true)
        timeWindow: String
    ): Response<SerieContainer>

    @GET("tv/{tv_id}/similar")
    suspend fun getSimilarSeries(
        @Path(value = "tv_id", encoded = true)
        serieId: Int
    ): Response<SerieContainer>
}
