package com.gabriel.data.usuario.dataSource

import com.gabriel.data.usuario.model.UsuarioData
import com.gabriel.domain.util.state.ResourceState

interface AutenticaUsuarioDataSource {
    suspend fun autenticaUsuario(usuario: UsuarioData): ResourceState<Boolean>
}