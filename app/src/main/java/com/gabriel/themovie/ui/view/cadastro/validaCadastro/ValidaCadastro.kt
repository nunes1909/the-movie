package com.gabriel.themovie.ui.view.cadastro.validaCadastro

import com.gabriel.domain.util.state.ResourceState
import com.gabriel.themovie.ui.view.validaFormulario.ValidaFormularioStrategy

class ValidaCadastro : ValidaFormularioStrategy {
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

        if (senha != confirmaSenha) {
            return ResourceState.Error(data = false, message = "Senhas diferentes")
        }

        return ResourceState.Success(true)
    }
}
