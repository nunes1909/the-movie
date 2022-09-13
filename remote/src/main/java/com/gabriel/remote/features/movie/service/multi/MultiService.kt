package com.gabriel.remote.features.movie.service.multi

import com.gabriel.remote.features.movie.modelsApi.multi.MultiContainer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MultiService {

    @GET("search/multi")
    fun searchMulti(
       @Query("query")
       query: String
    ): Response<MultiContainer>
}
