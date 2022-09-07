package com.gabriel.themovie.ui.views.filmes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabriel.domain.features.filme.model.FilmeDomain
import com.gabriel.domain.features.filme.useCase.GetFilmesUseCase
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.themovie.model.filme.mapper.FilmeViewMapper
import com.gabriel.themovie.model.filme.model.FilmeView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class FilmesViewModel(
    private val getFilmesUseCase: GetFilmesUseCase,
    private val mapper: FilmeViewMapper
) : ViewModel() {

    private val _list = MutableStateFlow<ResourceState<List<FilmeView>>>(ResourceState.Loading())
    val list: StateFlow<ResourceState<List<FilmeView>>> = _list

    init {
        getFilmes()
    }

    private fun getFilmes() = viewModelScope.launch {
        val resourceState = getFilmesUseCase.getAllFilmes()
        _list.value = safeState(resourceState)

    }

    private fun safeState(resourceState: ResourceState<List<FilmeDomain>>):
            ResourceState<List<FilmeView>> {
        if (resourceState.data != null) {
            val listView = mapper.mapToDomainNonNull(resourceState.data!!)
            Timber.tag("tagabriel").i("${listView.map { it.title }}")
            return ResourceState.Success(listView)
        }
        return ResourceState.Error(cod = resourceState.cod, message = resourceState.message)
    }

}
