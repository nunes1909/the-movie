package com.gabriel.themovie.ui.view.series

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.movie.useCase.*
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.themovie.movie.mapper.MovieViewMapper
import com.gabriel.themovie.movie.model.MovieView
import com.gabriel.themovie.util.constants.ConstantsView
import com.gabriel.themovie.util.constants.ConstantsView.TYPE_SERIE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SeriesViewModel(
    private val getAllMoviesUseCase: GetAllMoviesUseCase,
    private val getTrendingMovieUseCase: GetTrendingMovieUseCase,
    private val getDetailMovieUseCase: GetDetailMovieUseCase,
    private val saveMovieUseCase: SaveMovieUseCase,
    private val verifyExists: VerifyExistsMovieUseCase,
    private val deleteMovieUseCase: DeleteMovieUseCase,
    private val mapper: MovieViewMapper
) : ViewModel() {
    // Region StateFlow
    private val _list = MutableStateFlow<ResourceState<List<MovieView>>>(ResourceState.Loading())
    val list: StateFlow<ResourceState<List<MovieView>>> = _list

    private val _movieDetail =
        MutableStateFlow<ResourceState<MovieView>>(ResourceState.Loading())
    val movieDetail: StateFlow<ResourceState<MovieView>> = _movieDetail

    private val _verify = MutableStateFlow<Boolean>(true)
    val verify: StateFlow<Boolean> = _verify
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

    // Region tendência
    fun getTranding() = viewModelScope.launch {
        val resourceState = getTrendingMovieUseCase.getTrendingMovie(TYPE_SERIE)
        val safeState = safeStateTrandingSeries(resourceState)
        val firstMovie = getFirstMovie(safeState).also { it.type = TYPE_SERIE }
        getDetailMovie(firstMovie.type!!, firstMovie.id)
    }

    private fun safeStateTrandingSeries(resourceState: ResourceState<List<MovieDomain>>):
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
    private fun getDetailMovie(type: String, movieId: Int) {
        CoroutineScope(IO).launch {
            val resourceState = getDetailMovieUseCase.getDetailMovie(type = type, movieId = movieId)
            verifyExistsMovie(movieId = movieId)
            _movieDetail.value = safeStateGetDetailMovie(resourceState)
        }
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
            verifyExistsMovie(movieId = movieView.id)
        }
    }

    private fun safeSateSave(save: ResourceState<Boolean>): ResourceState<Boolean> {
        if (save.data != null) {
            return ResourceState.Success(save.data!!)
        }
        return ResourceState.Error(message = save.message)
    }
    // Endregion

    // Region verify if exists movie
    private fun verifyExistsMovie(movieId: Int) = viewModelScope.launch {
        _verify.value = verifyExists.verifyExistsMovie(id = movieId).first()
    }
    // Endregion

    // Region delete movie
    fun deleteMovie(movieView: MovieView) {
        CoroutineScope(Dispatchers.IO).launch {
            val movieDomain = mapper.mapToDomain(movieView)
            deleteMovieUseCase.delete(movieDomain)
            verifyExistsMovie(movieId = movieView.id)
        }
    }
    // Endregion
}
