package com.gabriel.themovie.ui.view.detalhes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.movie.useCase.GetDetailMovieUseCase
import com.gabriel.domain.movie.useCase.GetSimilarMoviesUseCase
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.themovie.movie.mapper.MovieViewMapper
import com.gabriel.themovie.movie.model.MovieView
import com.gabriel.themovie.util.constants.ConstantsView.TYPE_FILME
import com.gabriel.themovie.util.constants.ConstantsView.TYPE_SERIE
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetalhesViewModel(
    private val getDetailMovieUseCase: GetDetailMovieUseCase,
    private val getSimilarMoviesUseCase: GetSimilarMoviesUseCase,
    private val mapper: MovieViewMapper
) : ViewModel() {
    // Region StateFlow
    private val _movieDetail =
        MutableStateFlow<ResourceState<MovieView>>(ResourceState.Loading())
    val movieDetail: StateFlow<ResourceState<MovieView>> = _movieDetail

    private val _listSimilares =
        MutableStateFlow<ResourceState<List<MovieView>>>(ResourceState.Loading())
    val listSimilares: StateFlow<ResourceState<List<MovieView>>> = _listSimilares
    // Endregion

    // Region get movie
    fun getDetail(movie: MovieView) {
        when (movie.type) {
            TYPE_FILME -> {
                getDetailMovie(TYPE_FILME, movie.id)
                getSimilarMovies(TYPE_FILME, movie.id)
            }
            TYPE_SERIE -> {
                getDetailMovie(TYPE_SERIE, movie.id)
                getSimilarMovies(TYPE_SERIE, movie.id)
            }
            else -> {
                // sem impl
            }
        }
    }
    // Endregion

    // Region get details
    private fun getDetailMovie(type: String, movieId: Int) = viewModelScope.launch {
        val resourceState = getDetailMovieUseCase.getDetailMovie(type = type, movieId = movieId)
        _movieDetail.value = safeStateGetDetailMovie(resourceState)
    }

    private fun safeStateGetDetailMovie(resourceState: ResourceState<MovieDomain>):
            ResourceState<MovieView> {
        if (resourceState.data != null) {
            val movieView = mapper.mapFromDomain(resourceState.data!!)
            return ResourceState.Success(movieView)
        }
        return ResourceState.Error(cod = resourceState.cod, message = resourceState.message)
    }
    // Endregion

    // Region get similar movies
    fun getSimilarMovies(type: String, movieId: Int) = viewModelScope.launch {
        val resourceState = getSimilarMoviesUseCase.getSimilarMovies(type = type, movieId = movieId)
        _listSimilares.value = safeStateGetMovies(resourceState)
    }

    private fun safeStateGetMovies(resourceState: ResourceState<List<MovieDomain>>):
            ResourceState<List<MovieView>> {
        if (resourceState.data != null) {
            val listView = mapper.mapToDomainNonNull(resourceState.data!!)
            return ResourceState.Success(listView)
        }
        return ResourceState.Error(cod = resourceState.cod, message = resourceState.message)
    }
    // Endregion
}
