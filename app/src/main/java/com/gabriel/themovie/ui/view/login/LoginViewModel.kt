package com.gabriel.themovie.ui.view.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabriel.domain.usuario.useCase.AutenticaUsuarioUseCase
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.themovie.usuario.mapper.UsuarioViewMapper
import com.gabriel.themovie.usuario.model.UsuarioView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val mapper: UsuarioViewMapper,
    private val autenticaUsuarioUseCase: AutenticaUsuarioUseCase
) : ViewModel() {

    // region autentica
    private val _autentica = MutableStateFlow<ResourceState<Boolean>>(ResourceState.Empty())
    val autentica: StateFlow<ResourceState<Boolean>> = _autentica

    fun autenticaUsuario(usuarioView: UsuarioView) = viewModelScope.launch {
        val usuario = mapper.mapToDomain(usuarioView)
        _autentica.value = autenticaUsuarioUseCase.autenticaUsuario(usuario)
    }
    // endregion autentica
}
