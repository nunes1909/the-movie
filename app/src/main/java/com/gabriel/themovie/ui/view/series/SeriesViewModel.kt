package com.gabriel.themovie.ui.view.series

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.movie.useCase.GetAllMoviesUseCase
import com.gabriel.domain.movie.useCase.GetRecentMovieUseCase
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.themovie.movie.mapper.MovieViewMapper
import com.gabriel.themovie.movie.model.MovieView
import com.gabriel.themovie.util.constants.ConstantsView.TYPE_SERIE
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SeriesViewModel(
    private val getAllMoviesUseCase: GetAllMoviesUseCase,
    private val getRecentMovieUseCase: GetRecentMovieUseCase,
    private val mapper: MovieViewMapper
) : ViewModel() {

    private val _list = MutableStateFlow<ResourceState<List<MovieView>>>(ResourceState.Loading())
    val list: StateFlow<ResourceState<List<MovieView>>> = _list

    init {
        getSeries()
        getTranding()
    }

    private fun getSeries() = viewModelScope.launch {
        val resource = getAllMoviesUseCase.getAllMovies(TYPE_SERIE)
        _list.value = safeStateGetSeries(resource)
    }

    private fun safeStateGetSeries(resource: ResourceState<List<MovieDomain>>):
            ResourceState<List<MovieView>> {
        if (resource.data != null) {
            val listView = mapper.mapToDomainNonNull(resource.data!!)
            return ResourceState.Success(listView)
        }
        return ResourceState.Error(cod = resource.cod, message = resource.message)
    }

    private val _recent =
        MutableStateFlow<ResourceState<MovieView>>(ResourceState.Loading())
    val recent: StateFlow<ResourceState<MovieView>> = _recent

    fun getTranding() = viewModelScope.launch {
            val resourceState = getRecentMovieUseCase.getRecentMovie(TYPE_SERIE)
            _recent.value = safeStateTrandingFilmes(resourceState)
        }

    private fun safeStateTrandingFilmes(resourceState: ResourceState<MovieDomain>):
            ResourceState<MovieView> {
        if (resourceState.data != null) {
            val listView = mapper.mapFromDomain(resourceState.data!!)
            return ResourceState.Success(listView)
        }
        return ResourceState.Error(cod = resourceState.cod, message = resourceState.message)
    }
}
