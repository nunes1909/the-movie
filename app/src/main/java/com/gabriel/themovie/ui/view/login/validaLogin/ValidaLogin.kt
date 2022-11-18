package com.gabriel.themovie.ui.view.login.validaLogin

import com.gabriel.themovie.ui.view.validaFormulario.ValidaFormularioStrategy
import com.gabriel.domain.util.state.ResourceState

class ValidaLogin : ValidaFormularioStrategy {
    override fun validaCamposFormulario(
        nome: String?,
        email: String?,
        senha: String?,
        confirmaSenha: String?
    ): ResourceState<Boolean> {

        if (email!!.isBlank()) {
            return ResourceState.Error(data = false, message = "E-mail é necessário")
        }

        if (senha!!.isBlank()) {
            return ResourceState.Error(data = false, message = "Senha é necessária")
        }

        return ResourceState.Success(true)
    }
}