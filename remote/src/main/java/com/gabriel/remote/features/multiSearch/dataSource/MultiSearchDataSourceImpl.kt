package com.gabriel.remote.features.multiSearch.dataSource

import com.gabriel.data.features.multiSearch.dataSource.MultiSearchDataSource
import com.gabriel.data.features.multiSearch.model.MultiData
import com.gabriel.data.util.state.ResourceState
import com.gabriel.remote.features.multiSearch.mapper.MultiRemoteMapper
import com.gabriel.remote.features.multiSearch.model.MultiContainer
import com.gabriel.remote.features.multiSearch.service.MultiService
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

class MultiSearchDataSourceImpl(
    private val api: MultiService,
    private val mapper: MultiRemoteMapper
) : MultiSearchDataSource {
    override suspend fun searchMulti(query: String): ResourceState<List<MultiData>> {
        return try {
            val response = api.searchMulti(query = query)
            validateListResponse(response = response)
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    Timber.tag("GetFilmesDataSourceImpl/getAllFilmes").e("Error -> $t")
                    ResourceState.Undefined(message = "Erro de conexão.")
                }
                else -> {
                    Timber.tag("GetFilmesDataSourceImpl/getAllFilmes").e("Error -> $t")
                    ResourceState.Undefined(message = "Erro na conversão dos dados.")
                }
            }
        }
    }

    private fun validateListResponse(response: Response<MultiContainer>):
            ResourceState<List<MultiData>> {
        if (response.isSuccessful) {
            response.body()?.let { values ->
                val resultsData = mapper.mapFromDomainNonNull(values.results)
                return ResourceState.Undefined(data = resultsData)
            }
        }
        return ResourceState.Undefined(cod = response.code(), message = response.message())
    }
}
