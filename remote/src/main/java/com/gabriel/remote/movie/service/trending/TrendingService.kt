package com.gabriel.remote.movie.service.trending

import com.gabriel.remote.movie.modelsApi.filme.FilmeContainer
import com.gabriel.remote.movie.modelsApi.serie.SerieContainer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TrendingService {

    @GET("trending/{media_type}/{time_window}")
    suspend fun getTrendingFilme(
        @Path(value = "media_type")
        typeMovie: String,
        @Path(value = "time_window")
        intervalo: String = "day"
    ) : Response<FilmeContainer>

    @GET("trending/{media_type}/{time_window}")
    suspend fun getTrendingSerie(
        @Path(value = "media_type")
        typeSerie: String,
        @Path(value = "time_window")
        intervalo: String = "day"
    ) : Response<SerieContainer>
}
