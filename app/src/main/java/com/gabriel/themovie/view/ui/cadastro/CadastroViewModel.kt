package com.gabriel.themovie.view.ui.cadastro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabriel.domain.usuario.useCase.CadastraUsuarioUseCase
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.themovie.usuario.mapper.UsuarioViewMapper
import com.gabriel.themovie.usuario.model.UsuarioView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CadastroViewModel(
    private val mapper: UsuarioViewMapper,
    private val cadastraUsuarioUseCase: CadastraUsuarioUseCase
) : ViewModel() {

    // region cadastro
    private val _cadastra = MutableStateFlow<ResourceState<Boolean>>(ResourceState.Empty())
    val cadastra: StateFlow<ResourceState<Boolean>> = _cadastra

    fun cadastraUsuario(usuarioView: UsuarioView) = viewModelScope.launch {
        val usuario = mapper.mapToDomain(usuarioView)
        _cadastra.value = cadastraUsuarioUseCase.cadastraUsuario(usuario)
    }
    // endregion cadastro
}
