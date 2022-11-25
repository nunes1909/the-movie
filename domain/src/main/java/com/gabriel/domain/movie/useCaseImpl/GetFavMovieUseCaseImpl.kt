package com.gabriel.domain.movie.useCaseImpl

import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.movie.repository.GetFavMovieRespository
import com.gabriel.domain.movie.useCase.GetFavMovieUseCase
import com.gabriel.domain.util.state.ResourceState
import kotlinx.coroutines.flow.Flow

class GetFavMovieUseCaseImpl(private val repository: GetFavMovieRespository): GetFavMovieUseCase {
    override suspend fun getAllFav(query: String): ResourceState<List<MovieDomain>> {
        return repository.getAllFav(query = query)
    }
}