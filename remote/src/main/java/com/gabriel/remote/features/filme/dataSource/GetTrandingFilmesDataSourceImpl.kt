package com.gabriel.remote.features.filme.dataSource

import com.gabriel.data.features.filme.dataSource.GetTrandingFilmesDataSource
import com.gabriel.data.features.filme.model.FilmeData
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.remote.features.filme.model.FilmeContainer
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

class GetTrandingFilmesDataSourceImpl(
    private val api: FilmesService,
    private val mapper: FilmeRemoteMapper
) : GetTrandingFilmesDataSource {
    override suspend fun getTrending(
        mediaType: String,
        timeWindow: String
    ): ResourceState<List<FilmeData>> {
        return try {
            val response = api.getTrending(mediaType = mediaType, timeWindow = timeWindow)
            validaListResponse(response)
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    Timber.tag("GetTrandingFilmesDataSourceImpl/getTrending").e("Error -> $t")
                    ResourceState.Undefined(message = "Erro de conexão.")
                }
                else -> {
                    Timber.tag("GetTrandingFilmesDataSourceImpl/getTrending").e("Error -> $t")
                    ResourceState.Undefined(message = "Erro na conversão dos dados.")
                }
            }
        }
    }

    private fun validaListResponse(response: Response<FilmeContainer>): ResourceState<List<FilmeData>> {
        if (response.isSuccessful) {
            response.body()?.let { values ->
                val resultsData = mapper.mapFromDomainNonNull(values.results)
                return ResourceState.Undefined(data = resultsData)
            }
        }
        return ResourceState.Undefined(cod = response.code(), message = response.message())
    }
}
