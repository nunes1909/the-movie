package com.gabriel.themovie.ui.view.validaFormulario

import com.gabriel.domain.util.state.ResourceState

interface ValidaFormularioStrategy {
    fun validaCamposFormulario(
        nome: String? = null,
        email: String? = null,
        senha: String? = null,
        confirmaSenha: String? = null
    ): ResourceState<Boolean>
}
