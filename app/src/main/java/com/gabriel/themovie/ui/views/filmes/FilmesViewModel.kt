package com.gabriel.themovie.ui.views.filmes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabriel.domain.features.filme.model.FilmeDomain
import com.gabriel.domain.features.filme.useCase.GetFilmesUseCase
import com.gabriel.domain.features.filme.useCase.GetTrandingFilmesUseCase
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.themovie.model.filme.mapper.FilmeViewMapper
import com.gabriel.themovie.model.filme.model.FilmeView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class FilmesViewModel(
    private val getFilmesUseCase: GetFilmesUseCase,
    private val getTrandingFilmesUseCase: GetTrandingFilmesUseCase,
    private val mapper: FilmeViewMapper
) : ViewModel() {

    private val _list = MutableStateFlow<ResourceState<List<FilmeView>>>(ResourceState.Loading())
    val list: StateFlow<ResourceState<List<FilmeView>>> = _list

    init {
        getFilmes()
        getTranding()
    }

    private fun getFilmes() = viewModelScope.launch {
        val resourceState = getFilmesUseCase.getAllFilmes()
        _list.value = safeStateGetFilmes(resourceState)
    }

    private fun safeStateGetFilmes(resourceState: ResourceState<List<FilmeDomain>>):
            ResourceState<List<FilmeView>> {
        if (resourceState.data != null) {
            val listView = mapper.mapToDomainNonNull(resourceState.data!!)
            return ResourceState.Success(listView)
        }
        return ResourceState.Error(cod = resourceState.cod, message = resourceState.message)
    }

    private val _listTranding =
        MutableStateFlow<ResourceState<List<FilmeView>>>(ResourceState.Loading())
    val listTranding: StateFlow<ResourceState<List<FilmeView>>> = _listTranding

    fun getTranding(mediaType: String = "movie", timeWindow: String = "day") =
        viewModelScope.launch {
            val resourceState =
                getTrandingFilmesUseCase.getTrending(mediaType = mediaType, timeWindow = timeWindow)
            _listTranding.value = safeStateTrandingFilmes(resourceState)
        }

    private fun safeStateTrandingFilmes(resourceState: ResourceState<List<FilmeDomain>>):
            ResourceState<List<FilmeView>> {
        if (resourceState.data != null) {
            val listView = mapper.mapToDomainNonNull(resourceState.data!!)
            Timber.tag("tagabriel").i("${listView.map { it.title }}")
            return ResourceState.Success(listView)
        }
        return ResourceState.Error(cod = resourceState.cod, message = resourceState.message)
    }
}
