package com.gabriel.themovie.ui.views.detalhes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabriel.domain.features.filme.model.FilmeDomain
import com.gabriel.domain.features.filme.useCase.GetFilmesUseCase
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.themovie.model.filme.mapper.FilmeViewMapper
import com.gabriel.themovie.model.filme.model.FilmeView
import com.gabriel.themovie.model.multiMovie.model.MultiMovie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetalhesViewModel(
    private val getFilmesUseCase: GetFilmesUseCase,
    private val filmeViewMapper: FilmeViewMapper
) : ViewModel() {

    private val _filmeViewDetail =
        MutableStateFlow<ResourceState<FilmeView>>(ResourceState.Loading())
    val filmeViewDetail: StateFlow<ResourceState<FilmeView>> = _filmeViewDetail

    fun getDetail(movie: MultiMovie) {
        when (movie.type) {
            "filme" -> {
                getDetailFilme(movie.id)
            }
            "serie" -> {
                // sem impl
            }
            else -> {
                // sem impl
            }
        }
    }

    private fun getDetailFilme(filmeId: Int) = viewModelScope.launch {
        val resourceState = getFilmesUseCase.getDetailFilme(filmeId = filmeId)
        _filmeViewDetail.value = safeStateGetDetailFilme(resourceState)
    }

    private fun safeStateGetDetailFilme(resourceState: ResourceState<FilmeDomain>):
            ResourceState<FilmeView> {
        if (resourceState.data != null) {
            val filmeView = filmeViewMapper.mapFromDomain(resourceState.data!!)
            return ResourceState.Success(filmeView)
        }
        return ResourceState.Error(cod = resourceState.cod, message = resourceState.message)
    }
}
