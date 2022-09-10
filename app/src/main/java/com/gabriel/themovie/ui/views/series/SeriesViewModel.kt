package com.gabriel.themovie.ui.views.series

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabriel.domain.features.serie.model.SerieDomain
import com.gabriel.domain.features.serie.useCase.GetSeriesUseCase
import com.gabriel.domain.features.serie.useCase.GetTrandingSeriesUseCase
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.themovie.model.serie.mapper.SerieViewMapper
import com.gabriel.themovie.model.serie.model.SerieView
import com.gabriel.themovie.util.constants.ConstantsView.TYPE_SERIE
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SeriesViewModel(
    private val getSeriesUseCase: GetSeriesUseCase,
    private val getSeriesTrandingSeriesUseCase: GetTrandingSeriesUseCase,
    private val mapper: SerieViewMapper,
) : ViewModel() {

    private val _list = MutableStateFlow<ResourceState<List<SerieView>>>(ResourceState.Loading())
    val list: StateFlow<ResourceState<List<SerieView>>> = _list

    init {
        getSeries()
        getTranding()
    }

    private fun getSeries() = viewModelScope.launch {
        val resource = getSeriesUseCase.getAllSeries()
        _list.value = safeStateGetSeries(resource)
    }

    private fun safeStateGetSeries(resource: ResourceState<List<SerieDomain>>):
            ResourceState<List<SerieView>> {
        if (resource.data != null) {
            val listView = mapper.mapToDomainNonNull(resource.data!!)
            return ResourceState.Success(listView)
        }
        return ResourceState.Error(cod = resource.cod, message = resource.message)
    }

    private val _listTranding =
        MutableStateFlow<ResourceState<List<SerieView>>>(ResourceState.Loading())
    val listTranding: StateFlow<ResourceState<List<SerieView>>> = _listTranding

    fun getTranding(mediaType: String = TYPE_SERIE, timeWindow: String = "day") =
        viewModelScope.launch {
            val resourceState =
                getSeriesTrandingSeriesUseCase.getTrending(mediaType = mediaType, timeWindow = timeWindow)
            _listTranding.value = safeStateTrandingFilmes(resourceState)
        }

    private fun safeStateTrandingFilmes(resourceState: ResourceState<List<SerieDomain>>):
            ResourceState<List<SerieView>> {
        if (resourceState.data != null) {
            val listView = mapper.mapToDomainNonNull(resourceState.data!!)
            return ResourceState.Success(listView)
        }
        return ResourceState.Error(cod = resourceState.cod, message = resourceState.message)
    }
}
