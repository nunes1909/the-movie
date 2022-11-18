package com.gabriel.themovie.usuario.model

import java.io.Serializable

data class UsuarioView(
    val nome: String? = null,
    val email: String? = null,
    val senha: String? = null
) : Serializable
