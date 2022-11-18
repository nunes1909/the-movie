package com.gabriel.remote.usuario.mapper

import com.gabriel.data.usuario.model.UsuarioData
import com.gabriel.remote.usuario.model.UsuarioRemote
import com.gabriel.remote.util.base.RemoteMapper

class UsuarioRemoteMapper : RemoteMapper<UsuarioRemote, UsuarioData> {
    override fun mapToData(type: UsuarioRemote): UsuarioData {
        return UsuarioData(
            nome = type.nome,
            email = type.email,
            senha = type.senha
        )
    }

    override fun mapToRemote(type: UsuarioData): UsuarioRemote {
        return UsuarioRemote(
            nome = type.nome,
            email = type.email,
            senha = type.senha
        )
    }
}
