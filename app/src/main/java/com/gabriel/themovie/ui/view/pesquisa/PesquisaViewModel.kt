package com.gabriel.themovie.ui.view.pesquisa

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.movie.useCase.SearchMovieUseCase
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.themovie.movie.mapper.MovieViewMapper
import com.gabriel.themovie.movie.model.MovieView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PesquisaViewModel(
    private val searchMovieUseCase: SearchMovieUseCase,
    private val mapper: MovieViewMapper
) : ViewModel() {
    // Region StateFlow
    private val _search = MutableStateFlow<ResourceState<List<MovieView>>>(ResourceState.Empty())
    val search: StateFlow<ResourceState<List<MovieView>>> = _search
    // Endregion

    // Region search movie
    fun searchMovie(query: String) = viewModelScope.launch {
        val resourceState = searchMovieUseCase.searchMovie(query = query)
        _search.value = safeStateSearchMovie(resourceState)
    }

    private fun safeStateSearchMovie(resourceState: ResourceState<List<MovieDomain>>):
            ResourceState<List<MovieView>> {
        if (resourceState.data != null) {
            val listView = mapper.mapToViewNonNull(resourceState.data!!)
            return ResourceState.Success(listView)
        }
        return ResourceState.Error(cod = resourceState.cod, message = resourceState.message)
    }
    // Endregion
}
