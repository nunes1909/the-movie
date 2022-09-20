package com.gabriel.themovie.ui.view.favoritos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.movie.useCase.GetFavMovieUseCase
import com.gabriel.domain.movie.useCase.SaveMovieUseCase
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.themovie.movie.mapper.MovieViewMapper
import com.gabriel.themovie.movie.model.MovieView
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoritosViewModel(
    private val getFavMovieUseCase: GetFavMovieUseCase,
    private val mapper: MovieViewMapper
) : ViewModel() {
    // Region StateFlow
    private val _getFav = MutableStateFlow<ResourceState<List<MovieView>>>(ResourceState.Empty())
    val getFav: StateFlow<ResourceState<List<MovieView>>> = _getFav
    // Endregion

    init {
        getAllFav()
    }

    // Region get list fav
    fun getAllFav(query: String = "") = viewModelScope.launch {
        collectFlow(getFavMovieUseCase.getAllFav(query = query))
    }

    private suspend fun collectFlow(flowFav: Flow<ResourceState<List<MovieDomain>>>) {
        flowFav.collect {
            _getFav.value = safeStateGetFav(it)
        }
    }

    private fun safeStateGetFav(resource: ResourceState<List<MovieDomain>>):
            ResourceState<List<MovieView>> {
        if (resource.data != null && resource.data!!.isNotEmpty()) {
            val listView = mapper.mapToViewNonNull(resource.data!!)
            return ResourceState.Success(listView)
        }
        return ResourceState.Error(message = resource.message)
    }
    // Endregion
}
