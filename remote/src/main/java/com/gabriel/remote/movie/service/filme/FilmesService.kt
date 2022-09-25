package com.gabriel.remote.movie.service.filme

import com.gabriel.remote.movie.modelsApi.filme.FilmeContainer
import com.gabriel.remote.movie.modelsApi.filme.FilmeDetailResponse
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
    ): Response<FilmeDetailResponse>

    @GET("movie/latest")
    suspend fun getRecentFilme(): Response<FilmeDetailResponse>

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarFilmes(
        @Path(value = "movie_id", encoded = true)
        filmeId: Int
    ): Response<FilmeContainer>
}
