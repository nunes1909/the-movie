package com.gabriel.data.features.filme.dataSource

import com.gabriel.data.features.filme.model.FilmeData
import com.gabriel.domain.util.state.ResourceState

interface GetTrandingFilmesDataSource {
    suspend fun getTrending(mediaType: String, timeWindow: String = "day"):
            ResourceState<List<FilmeData>>
}
