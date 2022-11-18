package com.gabriel.themovie.usuario.mapper

import com.gabriel.domain.usuario.model.Usuario
import com.gabriel.themovie.usuario.model.UsuarioView
import com.gabriel.themovie.util.base.ViewMapper

class UsuarioViewMapper : ViewMapper<UsuarioView, Usuario> {
    override fun mapToDomain(type: UsuarioView): Usuario {
        return Usuario(
            nome = type.nome,
            email = type.email,
            senha = type.senha
        )
    }

    override fun mapToView(type: Usuario): UsuarioView {
        return UsuarioView(
            nome = type.nome,
            email = type.email,
            senha = type.senha
        )
    }
}
