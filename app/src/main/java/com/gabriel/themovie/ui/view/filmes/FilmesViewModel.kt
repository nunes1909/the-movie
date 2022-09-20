package com.gabriel.themovie.ui.view.filmes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.movie.useCase.GetAllMoviesUseCase
import com.gabriel.domain.movie.useCase.GetDetailMovieUseCase
import com.gabriel.domain.movie.useCase.GetTrendingMovieUseCase
import com.gabriel.domain.movie.useCase.SaveMovieUseCase
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.themovie.movie.mapper.MovieViewMapper
import com.gabriel.themovie.movie.model.MovieView
import com.gabriel.themovie.util.constants.ConstantsView.TYPE_FILME
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FilmesViewModel(
    private val getAllMoviesUseCase: GetAllMoviesUseCase,
    private val getTrendingMovieUseCase: GetTrendingMovieUseCase,
    private val getDetailMovieUseCase: GetDetailMovieUseCase,
    private val saveMovieUseCase: SaveMovieUseCase,
    private val mapper: MovieViewMapper
) : ViewModel() {
    // Region StateFlow
    private val _list = MutableStateFlow<ResourceState<List<MovieView>>>(ResourceState.Loading())
    val list: StateFlow<ResourceState<List<MovieView>>> = _list

    private val _movieDetail =
        MutableStateFlow<ResourceState<MovieView>>(ResourceState.Loading())
    val movieDetail: StateFlow<ResourceState<MovieView>> = _movieDetail
    // Endregion

    init {
        getFilmes()
        getTranding()
    }

    // Region get filmes populares
    private fun getFilmes() = viewModelScope.launch {
        val resourceState = getAllMoviesUseCase.getAllMovies(TYPE_FILME)
        _list.value = safeStateGetFilmes(resourceState)
    }

    private fun safeStateGetFilmes(resourceState: ResourceState<List<MovieDomain>>):
            ResourceState<List<MovieView>> {
        if (resourceState.data != null) {
            val listView = mapper.mapToViewNonNull(resourceState.data!!)
            return ResourceState.Success(listView)
        }
        return ResourceState.Error(cod = resourceState.cod, message = resourceState.message)
    }
    // Endregion

    // Region tendência
    private fun getTranding() = viewModelScope.launch {
        val resourceState = getTrendingMovieUseCase.getTrendingMovie(TYPE_FILME)
        val safeState = safeStateTrandingFilmes(resourceState)
        val firstMovie = getFirstMovie(safeState).also { it.type = TYPE_FILME }
        getDetailMovie(firstMovie.type!!, firstMovie.id)
    }

    private fun safeStateTrandingFilmes(resourceState: ResourceState<List<MovieDomain>>):
            ResourceState<List<MovieView>> {
        if (resourceState.data != null) {
            val listView = mapper.mapToViewNonNull(resourceState.data!!)
            return ResourceState.Success(listView)
        }
        return ResourceState.Error(cod = resourceState.cod, message = resourceState.message)
    }

    private fun getFirstMovie(safeState: ResourceState<List<MovieView>>): MovieView {
        return safeState.data?.sortedByDescending { it.nota }?.first()!!
    }

    // Region get details
    private fun getDetailMovie(type: String, movieId: Int) = viewModelScope.launch {
        val resourceState = getDetailMovieUseCase.getDetailMovie(type = type, movieId = movieId)
        _movieDetail.value = safeStateGetDetailMovie(resourceState)
    }
    private fun safeStateGetDetailMovie(resourceState: ResourceState<MovieDomain>):
            ResourceState<MovieView> {
        if (resourceState.data != null) {
            val movieView = mapper.mapToView(resourceState.data!!)
            return ResourceState.Success(movieView)
        }
        return ResourceState.Error(cod = resourceState.cod, message = resourceState.message)
    }
    // Endregion get details
    // Endregion tendência

    // Region save fav
    fun saveFavorito(movieView: MovieView) {
        CoroutineScope(IO).launch {
            val movieDomain = mapper.mapToDomain(movieView)
            safeSateSave(saveMovieUseCase.save(entity = movieDomain))
        }
    }

    private fun safeSateSave(save: ResourceState<Boolean>): ResourceState<Boolean> {
        if (save.data != null) {
            return ResourceState.Success(save.data!!)
        }
        return ResourceState.Error(message = save.message)
    }
    // Endregion
}
