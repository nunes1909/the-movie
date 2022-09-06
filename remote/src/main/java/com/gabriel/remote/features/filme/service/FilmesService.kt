package com.gabriel.remote.features.filme.service

import com.gabriel.remote.features.filme.model.FilmeContainer
import com.gabriel.remote.features.filme.model.FilmeDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FilmesService {

    @GET("movie/popular")
    suspend fun getAllFilmes(): Response<FilmeContainer>

    @GET("movie/{movie_id}")
    suspend fun getDetailFilme(
        @Path(value = "movie_id", encoded = true)
        filmeId: Int
    ) : Response<FilmeDetailResponse>
}
