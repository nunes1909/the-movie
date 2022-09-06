package com.gabriel.remote.features.multiSearch.service

import com.gabriel.remote.features.multiSearch.model.MultiContainer
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
