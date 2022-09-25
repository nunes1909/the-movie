package com.gabriel.domain.movie.useCaseImpl

import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.movie.repository.SearchMovieRepository
import com.gabriel.domain.movie.useCase.SearchMovieUseCase
import com.gabriel.domain.util.state.ResourceState

class SearchMovieUseCaseImpl(private val repository: SearchMovieRepository) : SearchMovieUseCase {
    override suspend fun searchMovie(query: String): ResourceState<List<MovieDomain>> {
        return repository.searchMovie(query = query)
    }
}
