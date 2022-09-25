package com.gabriel.remote.movie.service.traducao

import com.gabriel.remote.traducao.model.filme.TraducaoFilmeContainer
import com.gabriel.remote.traducao.model.serie.TraducaoSerieContainer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TraducaoService {
    @GET("movie/{movie_id}/translations")
    suspend fun getTraducaoFilme(
        @Path(value = "movie_id", encoded = true)
        filmeId: Int
    ): Response<TraducaoFilmeContainer>

    @GET("tv/{tv_id}/translations")
    suspend fun getTraducaoSerie(
        @Path(value = "tv_id", encoded = true)
        serieId: Int
    ): Response<TraducaoSerieContainer>
}
