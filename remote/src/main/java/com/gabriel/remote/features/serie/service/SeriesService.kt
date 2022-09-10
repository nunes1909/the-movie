package com.gabriel.remote.features.serie.service

import com.gabriel.remote.features.serie.model.SerieContainer
import com.gabriel.remote.features.serie.model.SerieDetailResponse
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
}
