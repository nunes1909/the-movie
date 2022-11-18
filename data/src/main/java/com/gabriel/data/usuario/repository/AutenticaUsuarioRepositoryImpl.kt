package com.gabriel.data.usuario.repository

import com.gabriel.data.usuario.dataStore.AutenticaUsuarioDataStore
import com.gabriel.data.usuario.mapper.UsuarioDataMapper
import com.gabriel.domain.usuario.model.Usuario
import com.gabriel.domain.usuario.repository.AutenticaUsuarioRepository
import com.gabriel.domain.util.state.ResourceState

class AutenticaUsuarioRepositoryImpl(
    private val dataStore: AutenticaUsuarioDataStore,
    private val mapper: UsuarioDataMapper
) : AutenticaUsuarioRepository {
    override suspend fun autenticaUsuario(usuario: Usuario): ResourceState<Boolean> {
        val usuarioData = mapper.mapToData(usuario)
        return dataStore.autenticaUsuario(usuarioData)
    }
}
