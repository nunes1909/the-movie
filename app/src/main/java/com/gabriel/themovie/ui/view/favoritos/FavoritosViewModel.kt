package com.gabriel.themovie.ui.view.favoritos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.movie.useCase.GetFavMovieUseCase
import com.gabriel.domain.movie.useCase.SaveMovieUseCase
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.themovie.movie.mapper.MovieViewMapper
import com.gabriel.themovie.movie.model.MovieView
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FavoritosViewModel(
    private val saveMovieUseCase: SaveMovieUseCase,
    private val getFavMovieUseCase: GetFavMovieUseCase,
    private val mapper: MovieViewMapper
) : ViewModel() {
    // Region StateFlow
    private val _save = MutableStateFlow<ResourceState<Boolean>>(ResourceState.Empty())
    val save: StateFlow<ResourceState<Boolean>> = _save

    private val _getFav = MutableStateFlow<ResourceState<List<MovieView>>>(ResourceState.Empty())
    val getFav: StateFlow<ResourceState<List<MovieView>>> = _getFav
    // Endregion

    init {
        getAllFav()
    }

    // Region save fav
    fun saveFavorito(movieView: MovieView) = viewModelScope.launch {
        val movieDomain = mapper.mapToDomain(movieView)
        _save.value = safeSateSave(saveMovieUseCase.save(entity = movieDomain))
    }

    private fun safeSateSave(save: ResourceState<Boolean>): ResourceState<Boolean> {
        if (save.data != null) {
            return ResourceState.Success(save.data!!)
        }
        return ResourceState.Error(message = save.message)
    }
    // Endregion

    // Region get list fav
    fun getAllFav() = viewModelScope.launch {
        collectFlow(getFavMovieUseCase.getAllFav())
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
