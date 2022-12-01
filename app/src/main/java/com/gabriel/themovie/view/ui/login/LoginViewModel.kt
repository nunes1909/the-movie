package com.gabriel.themovie.view.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabriel.domain.usuario.useCase.AutenticaUsuarioUseCase
import com.gabriel.domain.util.state.ResourceState
import com.gabriel.themovie.usuario.mapper.UsuarioViewMapper
import com.gabriel.themovie.usuario.model.UsuarioView
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

private const val TAG = "LoginViewModel"
private const val MSG = "autenticaUsuarioGoogle"

class LoginViewModel(
    private val mapper: UsuarioViewMapper,
    private val autenticaUsuarioUseCase: AutenticaUsuarioUseCase
) : ViewModel() {

    // region autentica
    private val _auth = MutableStateFlow<ResourceState<Boolean>>(ResourceState.Empty())
    val auth: StateFlow<ResourceState<Boolean>> = _auth

    fun autenticaUsuario(usuarioView: UsuarioView) = viewModelScope.launch {
        val usuario = mapper.mapToDomain(usuarioView)
        _auth.value = autenticaUsuarioUseCase.autenticaUsuario(usuario)
    }

    private val _authGoogle = MutableStateFlow<ResourceState<Boolean>>(ResourceState.Empty())
    val authGoogle: StateFlow<ResourceState<Boolean>> = _authGoogle

    fun autenticaUsuarioGoogle(task: Task<GoogleSignInAccount>) = viewModelScope.launch {
        try {
            task.result.idToken?.let {
                _authGoogle.value = autenticaUsuarioUseCase.autenticaGoogle((it))
            }
        } catch (e: Exception) {
            Timber.tag(TAG).e(e, MSG)
        }
    }
    // endregion autentica
}
