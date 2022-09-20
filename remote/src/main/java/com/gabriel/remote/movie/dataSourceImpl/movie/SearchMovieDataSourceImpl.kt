package com.gabriel.remote.movie.dataSourceImpl.movie

import com.gabriel.data.movie.dataSource.movie.SearchMovieDataSource
import com.gabriel.data.movie.model.MovieData
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.remote.movie.mapper.multi.MultiRemoteToMovieMapper
import com.gabriel.remote.movie.modelsApi.multi.MultiContainer
import com.gabriel.remote.movie.service.multi.MultiService
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

class SearchMovieDataSourceImpl(
    private val service: MultiService,
    private val mapper: MultiRemoteToMovieMapper
) : SearchMovieDataSource {
    override suspend fun searchMovie(query: String): ResourceState<List<MovieData>> {
        return try {
            val response = service.searchMulti(query = query)
            validateListResponse(response = response)
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    Timber
                        .tag("GetFilmesDataSourceImpl/searchMulti")
                        .e("Error -> $t")
                    ResourceState.Undefined(message = "Erro de conexão.")
                }
                else -> {
                    Timber
                        .tag("GetFilmesDataSourceImpl/searchMulti")
                        .e("Error -> $t")
                    ResourceState.Undefined(message = "Erro na conversão dos dados.")
                }
            }
        }
    }

    private fun validateListResponse(response: Response<MultiContainer>):
            ResourceState<List<MovieData>> {
        if (response.isSuccessful) {
            response.body()?.let { values ->
                val resultsData = mapper.mapToDataNonNull(values.results)
                return ResourceState.Undefined(data = resultsData)
            }
        }
        return ResourceState.Undefined(cod = response.code(), message = response.message())
    }
}
