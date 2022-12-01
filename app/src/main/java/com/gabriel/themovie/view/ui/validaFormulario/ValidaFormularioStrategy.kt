package com.gabriel.themovie.view.ui.validaFormulario

import com.gabriel.domain.util.state.ResourceState

interface ValidaFormularioStrategy {
    fun validaCamposFormulario(
        nome: String? = null,
        email: String? = null,
        senha: String? = null,
        confirmaSenha: String? = null
    ): ResourceState<Boolean>
}
