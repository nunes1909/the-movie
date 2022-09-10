package com.gabriel.themovie.ui.views.detalhes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabriel.domain.features.filme.model.FilmeDomain
import com.gabriel.domain.features.filme.useCase.GetFilmesSimilaresUseCase
import com.gabriel.domain.features.filme.useCase.GetFilmesUseCase
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.themovie.model.filme.mapper.FilmeViewMapper
import com.gabriel.themovie.model.filme.model.FilmeView
import com.gabriel.themovie.model.multiMovie.mapper.MultiMovieMapper
import com.gabriel.themovie.model.multiMovie.model.MultiMovie
import com.gabriel.themovie.util.constants.ConstantsView.TYPE_FILME
import com.gabriel.themovie.util.constants.ConstantsView.TYPE_SERIE
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetalhesViewModel(
    private val getFilmesUseCase: GetFilmesUseCase,
    private val getFilmesSimilaresUseCase: GetFilmesSimilaresUseCase,
    private val filmeViewMapper: FilmeViewMapper,
    private val multiMovieMapper: MultiMovieMapper
) : ViewModel() {

    /**
     * Região dos detalhes de um [movie]
     * Foi optado por um metodo universal, pois o [DetalhesFragment] não espera uma model
     * específica.
     */
    private val _multiMovieDetail =
        MutableStateFlow<ResourceState<MultiMovie>>(ResourceState.Loading())
    val multiMovieDetail: StateFlow<ResourceState<MultiMovie>> = _multiMovieDetail

    fun getDetail(movie: MultiMovie) {
        when (movie.type) {
            TYPE_FILME -> {
                getDetailFilme(movie.id)
                getFilmesSimilares(movie.id)
            }
            TYPE_SERIE -> {
                // sem impl
            }
            else -> {
                // sem impl
            }
        }
    }

    /**
     * Região da busca pelos detalhes de um filme.
     */
    private fun getDetailFilme(filmeId: Int) = viewModelScope.launch {
        val resourceState = getFilmesUseCase.getDetailFilme(filmeId = filmeId)
        _multiMovieDetail.value = safeStateGetDetailFilme(resourceState)
    }
    private fun safeStateGetDetailFilme(resourceState: ResourceState<FilmeDomain>):
            ResourceState<MultiMovie> {
        if (resourceState.data != null) {
            val filmeView = filmeViewMapper.mapFromDomain(resourceState.data!!)
            val multiMovie = multiMovieMapper.mapFromDomain(filmeView)
            return ResourceState.Success(multiMovie)
        }
        return ResourceState.Error(cod = resourceState.cod, message = resourceState.message)
    }

    /**
     * Região dos filmes similares.
     * Foi optado por fazer as buscas de filmes e series similares de forma separa. Isso para que
     * não fosse necessário modificar os models esperados pelos adapters das recycler view.
     */
    private val _listSimilares = MutableStateFlow<ResourceState<List<FilmeView>>>(ResourceState.Loading())
    val listSimilares: StateFlow<ResourceState<List<FilmeView>>> = _listSimilares

    fun getFilmesSimilares(filmeId: Int) = viewModelScope.launch {
        val resourceState = getFilmesSimilaresUseCase.getFilmesSimilares(filmeId = filmeId)
        _listSimilares.value = safeStateGetFilmes(resourceState)
    }

    private fun safeStateGetFilmes(resourceState: ResourceState<List<FilmeDomain>>):
            ResourceState<List<FilmeView>> {
        if (resourceState.data != null) {
            val listView = filmeViewMapper.mapToDomainNonNull(resourceState.data!!)
            return ResourceState.Success(listView)
        }
        return ResourceState.Error(cod = resourceState.cod, message = resourceState.message)
    }
}
