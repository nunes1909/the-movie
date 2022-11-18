package com.gabriel.data.usuario.dataStoreImpl

import com.gabriel.data.usuario.dataSource.CadastraUsuarioDataSource
import com.gabriel.data.usuario.dataStore.CadastraUsuarioDataStore
import com.gabriel.data.usuario.model.UsuarioData
import com.gabriel.domain.util.state.ResourceState

class CadastraUsuarioDataStoreImpl(private val dataSource: CadastraUsuarioDataSource) :
    CadastraUsuarioDataStore {
    override suspend fun cadastraUsuario(usuario: UsuarioData): ResourceState<Boolean> {
        return dataSource.cadastraUsuario(usuario)
    }
}
