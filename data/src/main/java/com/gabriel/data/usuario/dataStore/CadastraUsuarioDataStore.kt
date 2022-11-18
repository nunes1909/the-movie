package com.gabriel.data.usuario.dataStore

import com.gabriel.data.usuario.model.UsuarioData
import com.gabriel.domain.util.state.ResourceState

interface CadastraUsuarioDataStore {
    suspend fun cadastraUsuario(usuario: UsuarioData): ResourceState<Boolean>
}
