package com.gabriel.themovie.ui.view.series

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.movie.useCase.GetAllMoviesUseCase
import com.gabriel.domain.movie.useCase.GetTrendingMovieUseCase
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.themovie.movie.mapper.MovieViewMapper
import com.gabriel.themovie.movie.model.MovieView
import com.gabriel.themovie.util.constants.ConstantsView.TYPE_SERIE
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SeriesViewModel(
    private val getAllMoviesUseCase: GetAllMoviesUseCase,
    private val getTrendingMovieUseCase: GetTrendingMovieUseCase,
    private val mapper: MovieViewMapper
) : ViewModel() {
    // Region StateFlow
    private val _list = MutableStateFlow<ResourceState<List<MovieView>>>(ResourceState.Loading())
    val list: StateFlow<ResourceState<List<MovieView>>> = _list

    private val _trending =
        MutableStateFlow<ResourceState<List<MovieView>>>(ResourceState.Loading())
    val trending: StateFlow<ResourceState<List<MovieView>>> = _trending
    // Endregion

    init {
        getSeries()
        getTranding()
    }

    // Region get series populares
    private fun getSeries() = viewModelScope.launch {
        val resource = getAllMoviesUseCase.getAllMovies(TYPE_SERIE)
        _list.value = safeStateGetSeries(resource)
    }

    private fun safeStateGetSeries(resource: ResourceState<List<MovieDomain>>):
            ResourceState<List<MovieView>> {
        if (resource.data != null) {
            val listView = mapper.mapToViewNonNull(resource.data!!)
            return ResourceState.Success(listView)
        }
        return ResourceState.Error(cod = resource.cod, message = resource.message)
    }
    // Endregion

    // Region get series tendência
    fun getTranding() = viewModelScope.launch {
        val resourceState = getTrendingMovieUseCase.getTrendingMovie(TYPE_SERIE)
        _trending.value = safeStateTrandingSeries(resourceState)
    }

    private fun safeStateTrandingSeries(resourceState: ResourceState<List<MovieDomain>>):
            ResourceState<List<MovieView>> {
        if (resourceState.data != null) {
            val listView = mapper.mapToViewNonNull(resourceState.data!!)
            return ResourceState.Success(listView)
        }
        return ResourceState.Error(cod = resourceState.cod, message = resourceState.message)
    }
    // Endregion
}
