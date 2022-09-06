package com.gabriel.remote.features.genero.service

import com.gabriel.remote.features.genero.model.GeneroResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GeneroService {

    @GET("genre/movie/list")
    suspend fun getGeneroFilme(): Response<GeneroResponse>

    @GET("genre/tv/list")
    suspend fun getGeneroSerie(): Response<GeneroResponse>
}
