package com.gabriel.themovie.ui.view.favoritos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabriel.domain.movie.useCase.SaveMovieUseCase
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.themovie.movie.mapper.MovieViewMapper
import com.gabriel.themovie.movie.model.MovieView
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FavoritosViewModel(
    private val saveMovieUseCase: SaveMovieUseCase,
    private val mapper: MovieViewMapper
) : ViewModel() {
    // Region StateFlow
    private val _save = MutableStateFlow<ResourceState<Boolean>>(ResourceState.Empty())
    val save: StateFlow<ResourceState<Boolean>> = _save
    // Endregion

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
}
