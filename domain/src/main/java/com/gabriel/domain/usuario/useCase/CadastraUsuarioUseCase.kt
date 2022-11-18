package com.gabriel.domain.usuario.useCase

import com.gabriel.domain.usuario.model.Usuario
import com.gabriel.domain.util.state.ResourceState

interface CadastraUsuarioUseCase {
    suspend fun cadastraUsuario(usuario: Usuario): ResourceState<Boolean>
}
