package com.gabriel.themovie.ui.view.detalhes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabriel.domain.movie.model.MovieDomain
import com.gabriel.domain.movie.useCase.*
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.themovie.movie.mapper.MovieViewMapper
import com.gabriel.themovie.movie.model.MovieView
import com.gabriel.themovie.util.constants.ConstantsView.TYPE_FILME
import com.gabriel.themovie.util.constants.ConstantsView.TYPE_SERIE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class DetalhesViewModel(
    private val getDetailMovieUseCase: GetDetailMovieUseCase,
    private val getSimilarMoviesUseCase: GetSimilarMoviesUseCase,
    private val saveMovieUseCase: SaveMovieUseCase,
    private val verifyExists: VerifyExistsMovieUseCase,
    private val deleteMovieUseCase: DeleteMovieUseCase,
    private val mapper: MovieViewMapper
) : ViewModel() {
    // Region StateFlow
    private val _movieDetail =
        MutableStateFlow<ResourceState<MovieView>>(ResourceState.Loading())
    val movieDetail: StateFlow<ResourceState<MovieView>> = _movieDetail

    private val _listSimilares =
        MutableStateFlow<ResourceState<List<MovieView>>>(ResourceState.Loading())
    val listSimilares: StateFlow<ResourceState<List<MovieView>>> = _listSimilares

    private val _verify = MutableStateFlow<Boolean>(true)
    val verify: StateFlow<Boolean> = _verify
    // Endregion

    // Region get movie
    /**
     * Ao buscar os detalhes ja é verificado se existe ou não o movie salvo.
     * Caso exista, o status do checkbox de save é alterado
     *
     * @param verifyExistsMovie
     */
    fun getDetail(movie: MovieView) {
        when (movie.type) {
            TYPE_FILME -> {
                getDetailMovie(type = TYPE_FILME, movieId = movie.id)
                getSimilarMovies(type = TYPE_FILME, movieId = movie.id)
                verifyExistsMovie(movieId = movie.id)
            }
            TYPE_SERIE -> {
                getDetailMovie(type = TYPE_SERIE, movieId = movie.id)
                getSimilarMovies(type = TYPE_SERIE, movieId = movie.id)
                verifyExistsMovie(movieId = movie.id)
            }
            else -> {
                // sem impl
            }
        }
    }
    // Endregion

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
    // Endregion

    // Region get similar movies
    private fun getSimilarMovies(type: String, movieId: Int) = viewModelScope.launch {
        val resourceState = getSimilarMoviesUseCase.getSimilarMovies(type = type, movieId = movieId)
        _listSimilares.value = safeStateGetMovies(resourceState)
    }

    private fun safeStateGetMovies(resourceState: ResourceState<List<MovieDomain>>):
            ResourceState<List<MovieView>> {
        if (resourceState.data != null) {
            val listView = mapper.mapToViewNonNull(resourceState.data!!)
            return ResourceState.Success(listView)
        }
        return ResourceState.Error(cod = resourceState.cod, message = resourceState.message)
    }
    // Endregion

    // Region save fav
    fun saveFavorito(movieView: MovieView) {
        CoroutineScope(IO).launch {
            val movieDomain = mapper.mapToDomain(movieView)
            saveMovieUseCase.save(entity = movieDomain)
        }
    }
    // Endregion

    // Region verify if exists movie
    private fun verifyExistsMovie(movieId: Int) = viewModelScope.launch {
        verifyExists.verifyExistsMovie(id = movieId).collect { ifExists ->
            _verify.value = ifExists
        }
    }
    // Endregion

    // Region delete movie
    fun deleteMovie(movieView: MovieView) {
        CoroutineScope(IO).launch {
            val movieDomain = mapper.mapToDomain(movieView)
            deleteMovieUseCase.delete(movieDomain)
        }
    }
    // Endregion
}
