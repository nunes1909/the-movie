package com.gabriel.domain.usuario.useCaseImpl

import com.gabriel.domain.usuario.model.Usuario
import com.gabriel.domain.usuario.repository.AutenticaUsuarioRepository
import com.gabriel.domain.usuario.useCase.AutenticaUsuarioUseCase
import com.gabriel.domain.util.state.ResourceState

class AutenticaUsuarioUseCaseImpl(private val repository: AutenticaUsuarioRepository) :
    AutenticaUsuarioUseCase {
    override suspend fun autenticaUsuario(usuario: Usuario): ResourceState<Boolean> {
        return repository.autenticaUsuario(usuario)
    }
}
