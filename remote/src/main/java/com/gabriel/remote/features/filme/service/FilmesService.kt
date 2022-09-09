package com.gabriel.remote.features.filme.service

import com.gabriel.remote.features.filme.model.FilmeContainer
import com.gabriel.remote.features.filme.model.FilmeDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmesService {

    @GET("movie/popular")
    suspend fun getAllFilmes(): Response<FilmeContainer>

    @GET("movie/{movie_id}")
    suspend fun getDetailFilme(
        @Path(value = "movie_id", encoded = true)
        filmeId: Int
    ) : Response<FilmeDetailResponse>

    @GET("trending/{media_type}/{time_window}")
    suspend fun getTrending(
        @Path(value = "media_type", encoded = true)
        mediaType: String,
        @Path(value = "time_window", encoded = true)
        timeWindow: String
    ): Response<FilmeContainer>

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path(value = "movie_id", encoded = true)
        filmeId: Int
    ) : Response<FilmeContainer>
}
