package com.gabriel.domain.features.movie.useCaseImpl

import com.gabriel.domain.features.movie.model.MovieDomain
import com.gabriel.domain.features.movie.repository.SearchMovieRepository
import com.gabriel.domain.features.movie.useCase.SearchMovieUseCase
import com.gabriel.domain.util.state.ResourceState

class SearchMovieUseCaseImpl(private val repository: SearchMovieRepository) : SearchMovieUseCase {
    override suspend fun searchMovie(query: String): ResourceState<List<MovieDomain>> {
        return repository.searchMovie(query = query)
    }
}
