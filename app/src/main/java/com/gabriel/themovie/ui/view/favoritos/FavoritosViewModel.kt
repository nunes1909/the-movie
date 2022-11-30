package com.gabriel.themovie.ui.view.favoritos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.movie.useCase.DeleteMovieUseCase
import com.gabriel.domain.movie.useCase.GetFavMovieUseCase
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.themovie.movie.mapper.MovieViewMapper
import com.gabriel.themovie.movie.model.MovieView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoritosViewModel(
    private val getFavMovieUseCase: GetFavMovieUseCase,
    private val deleteMovieUseCase: DeleteMovieUseCase,
    private val mapper: MovieViewMapper
) : ViewModel() {

    init {
        getAllFav()
    }

    // Region StateFlow
    private val _getFav = MutableStateFlow<ResourceState<List<MovieView>>>(ResourceState.Empty())
    val getFav: StateFlow<ResourceState<List<MovieView>>> = _getFav
    // Endregion

    // Region get list fav
    fun getAllFav(query: String = "") = viewModelScope.launch {
        _getFav.value = safeStateGetFav(getFavMovieUseCase.getAllFav(query = query))
    }

    private fun safeStateGetFav(resource: ResourceState<List<MovieDomain>>):
            ResourceState<List<MovieView>> {
        if (!resource.data.isNullOrEmpty()) {
            val listView = mapper.mapToViewNonNull(resource.data!!)
            return ResourceState.Success(listView)
        }
        return ResourceState.Error(message = resource.message)
    }
    // Endregion

    // Region delete movie
    fun deleteMovie(movieView: MovieView) {
        CoroutineScope(Dispatchers.IO).launch {
            val movieDomain = mapper.mapToDomain(movieView)
            deleteMovieUseCase.delete(movieDomain)
        }
    }
    // Endregion
}
