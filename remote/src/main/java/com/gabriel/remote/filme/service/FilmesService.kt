package com.gabriel.remote.filme.service

import com.gabriel.remote.filme.model.FilmeContainer
import com.gabriel.remote.filme.model.FilmeDetailResponse
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

    @GET("search/movie")
    suspend fun searchFilmes(
        @Query("query")
        query: String
    ) : Response<FilmeContainer>

}