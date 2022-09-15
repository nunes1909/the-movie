package com.gabriel.themovie.ui.view.filmes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.movie.useCase.GetAllMoviesUseCase
import com.gabriel.domain.movie.useCase.GetTrendingMovieUseCase
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.themovie.movie.mapper.MovieViewMapper
import com.gabriel.themovie.movie.model.MovieView
import com.gabriel.themovie.util.constants.ConstantsView.TYPE_FILME
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FilmesViewModel(
    private val getAllMoviesUseCase: GetAllMoviesUseCase,
    private val getTrendingMovieUseCase: GetTrendingMovieUseCase,
    private val mapper: MovieViewMapper
) : ViewModel() {

    private val _list = MutableStateFlow<ResourceState<List<MovieView>>>(ResourceState.Loading())
    val list: StateFlow<ResourceState<List<MovieView>>> = _list

    init {
        getFilmes()
        getTranding()
    }

    private fun getFilmes() = viewModelScope.launch {
        val resourceState = getAllMoviesUseCase.getAllMovies(TYPE_FILME)
        _list.value = safeStateGetFilmes(resourceState)
    }

    private fun safeStateGetFilmes(resourceState: ResourceState<List<MovieDomain>>):
            ResourceState<List<MovieView>> {
        if (resourceState.data != null) {
            val listView = mapper.mapToDomainNonNull(resourceState.data!!)
            return ResourceState.Success(listView)
        }
        return ResourceState.Error(cod = resourceState.cod, message = resourceState.message)
    }

    private val _trending =
        MutableStateFlow<ResourceState<List<MovieView>>>(ResourceState.Loading())
    val trending: StateFlow<ResourceState<List<MovieView>>> = _trending

    private fun getTranding() = viewModelScope.launch {
        val resourceState = getTrendingMovieUseCase.getTrendingMovie(TYPE_FILME)
        _trending.value = safeStateTrandingFilmes(resourceState)
    }

    private fun safeStateTrandingFilmes(resourceState: ResourceState<List<MovieDomain>>):
            ResourceState<List<MovieView>> {
        if (resourceState.data != null) {
            val listView = mapper.mapToDomainNonNull(resourceState.data!!)
            return ResourceState.Success(listView)
        }
        return ResourceState.Error(cod = resourceState.cod, message = resourceState.message)
    }
}
