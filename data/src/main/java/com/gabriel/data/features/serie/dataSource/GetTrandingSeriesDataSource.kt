package com.gabriel.data.features.serie.dataSource

import com.gabriel.data.features.serie.model.SerieData
import com.gabriel.domain.util.state.ResourceState

interface GetTrandingSeriesDataSource {
    suspend fun getTrending(mediaType: String, timeWindow: String):
            ResourceState<List<SerieData>>
}
