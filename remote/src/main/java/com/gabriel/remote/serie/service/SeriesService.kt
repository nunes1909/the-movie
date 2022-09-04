package com.gabriel.remote.serie.service

import com.gabriel.remote.serie.model.SerieContainer
import com.gabriel.remote.serie.model.SerieDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SeriesService {

    @GET("tv/popular")
    fun getAllSeries(): Response<SerieContainer>

    @GET("tv/{tv_id}")
    fun getDetailSerie(
        @Path(value = "tv_id", encoded = true)
        serieId: Int
    ): Response<SerieDetailResponse>
}
