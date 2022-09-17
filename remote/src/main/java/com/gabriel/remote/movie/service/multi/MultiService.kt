package com.gabriel.remote.movie.service.multi

import com.gabriel.remote.movie.modelsApi.multi.MultiContainer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MultiService {
    @GET("search/multi")
    suspend fun searchMulti(
        @Query("query")
        query: String = "thor"
    ): Response<MultiContainer>
}
