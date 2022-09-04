package com.gabriel.remote.genero.service

import com.gabriel.remote.genero.model.GeneroResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GeneroService {

    @GET("genre/movie/list")
    suspend fun getGenero(
        @Query("api_key")
        apiKey: String
    ): Response<GeneroResponse>
}
