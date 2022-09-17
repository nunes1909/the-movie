package com.gabriel.domain.movie.useCaseImpl

import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.movie.repository.SaveMovieRepository
import com.gabriel.domain.movie.useCase.SaveMovieUseCase
import com.gabriel.domain.util.state.ResourceState
import kotlinx.coroutines.flow.Flow

class SaveMovieUseCaseImpl(private val repository: SaveMovieRepository): SaveMovieUseCase {
    override fun save(entity: MovieDomain): Flow<ResourceState<Boolean>> {
        return repository.save(entity = entity)
    }
}
